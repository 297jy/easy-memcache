package server

import (
	"fmt"
	"github.com/samuel/go-zookeeper/zk"
	"testing"
	"time"
)

func TestZookeeper(t *testing.T) {
	// 创建zk连接地址
	hosts := []string{"192.168.136.119:2181"}
	// 连接zk
	conn, _, err := zk.Connect(hosts, time.Second*5)
	defer conn.Close()
	if err != nil {
		fmt.Println(err)
		return
	}
	s := Server{Ip: "127.0.0.1", Host: "testhost"}
	regerr := s.register(conn)
	if regerr != nil {
		fmt.Println(regerr)
	}
	s.discover(conn)
}
