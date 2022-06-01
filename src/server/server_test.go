package server

import (
	"fmt"
	"github.com/samuel/go-zookeeper/zk"
	"log"
	"net/http"
	"testing"
	"time"
)

func TestZookeeper(t *testing.T) {

	s := NewServer("test")

	// 创建zk连接地址
	hosts := []string{"192.168.136.119:2181"}
	// 连接zk
	localConn, _, err := zk.Connect(hosts, time.Second*5)
	conn = localConn
	defer conn.Close()
	if err != nil {
		fmt.Println(err)
		return
	}
	go s.Watch()
	//s.Online()
	/**
	_, _, _, err = conn.ChildrenW(RegisterPath)
	if err != nil {
		fmt.Println(err)
		return
	}**/

	addr := "localhost:9999"
	log.Println("geecache is running at", addr)
	log.Fatal(http.ListenAndServe(addr, s))
}

func TestStr(t *testing.T) {
	c := "xxxx"
	p := "test_xxxxx_abc_test"
	fmt.Println(p[len(c):], "_", 2)
}
