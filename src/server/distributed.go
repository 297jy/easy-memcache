package server

import (
	"encoding/json"
	"fmt"
	"github.com/samuel/go-zookeeper/zk"
)

func (s *Server) discover() error {
	Provides = make([]Server, 0)
	names, _, err := conn.Children(RegisterPath)
	if err != nil {
		return err
	}
	for _, name := range names {
		data, _, gerr := conn.Get(RegisterPath + "/" + name)
		if gerr != nil {
			fmt.Println(gerr)
			continue
		}
		var server Server
		uerr := json.Unmarshal(data, &server)
		if uerr != nil {
			continue
		}
		Provides = append(Provides, server)
	}
	fmt.Println(Provides)
	return nil
}

func (s *Server) register() error {
	if e, _, err := conn.Exists(RegisterPath); !e || err != nil {
		_, cerr := conn.Create(RegisterPath, []byte{}, 0, zk.WorldACL(zk.PermAll))
		if cerr != nil {
			return cerr
		}
	}
	data, merr := json.Marshal(s)
	if merr != nil {
		return merr
	}
	_, cerr := conn.Create(RegisterPath+"/"+s.Name, data, zk.FlagEphemeral, zk.WorldACL(zk.PermAll))
	if cerr != nil {
		fmt.Println(cerr)
		return cerr
	}
	return nil
}
