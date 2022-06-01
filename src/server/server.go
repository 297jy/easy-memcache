package server

import (
	"fmt"
	"github.com/samuel/go-zookeeper/zk"
	"log"
	"net/http"
	"strings"
	"time"
)

const RegisterPath = "/easy-memcache-service"

const defaultBasePath = "/_easycache/"

type Service interface {
	discover() error

	register() error

	Online()

	Watch(event zk.Event)
}

type Server struct {
	Ip   string
	Name string
}

var LocalServer Server

var Provides []Server

var conn *zk.Conn

func NewServer(name string) *Server {
	s := &Server{
		Ip:   fmt.Sprintf("%d", time.Now().Unix()),
		Name: name,
	}
	return s
}

func (s *Server) String() string {
	return fmt.Sprintf("[ip => %v, host => %v]", s.Ip, s.Name)
}

func (s *Server) Log(format string, v ...interface{}) {
	log.Printf("Server %s[%s], %s", s.Ip, s.Name, fmt.Sprintf(format, v...))
}

func (s *Server) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	if !strings.HasPrefix(r.URL.Path, defaultBasePath) {
		panic("Server unexpected path: " + r.URL.Path)
	}
	s.Log("%s %s", r.Method, r.URL.Path)

	parts := strings.SplitN(r.URL.Path[len(defaultBasePath):], "/", 2)
	if len(parts) != 2 {
		http.Error(w, "bad request", http.StatusBadRequest)
		return
	}

	groupName := parts[0]
	key := parts[1]

	fmt.Println(groupName + key)
}

func (s *Server) Watch() {
	for {
		_, _, e, err := conn.ChildrenW(RegisterPath)
		if err != nil {
			panic(err)
		}
		event := <-e
		fmt.Println("###########################")
		fmt.Println("path: ", event.Path)
		fmt.Println("type: ", event.Type.String())
		fmt.Println("state: ", event.State.String())
		fmt.Println("---------------------------")
		// 只有子节点变化才重新开始服务发现
		if event.Type == zk.EventNodeChildrenChanged {
			_ = s.discover()
		}
	}
}

func (s *Server) Online() {
	_ = s.register()
}
