package cache

import (
	"EasyMemcache/src/common"
	"fmt"
	"strconv"
	"sync"
	"unsafe"
)

type Cache interface {
	Put(key string, value ByteView) (oldValue ByteView, ok bool)
	Get(key string) (value ByteView, ok bool)
	Eliminate()
}

type Value interface {
	Len() int
}

type ByteView struct {
	b       []byte
	version int
}

func (v ByteView) Len() int {
	return len(v.b) + int(unsafe.Sizeof(v.version))
}

func (v ByteView) Version() int {
	return v.version
}

func (v ByteView) ByteSlice() []byte {
	return cloneBytes(v.b)
}

func (v ByteView) String() string {
	return string(v.b)
}

func cloneBytes(b []byte) []byte {
	c := make([]byte, len(b))
	copy(c, b)
	return c
}

type entry struct {
	key   string
	value ByteView
}

type Getter interface {
	Get(key string) ([]byte, error)
}

type GetterFunc func(key string) ([]byte, error)

func (f GetterFunc) Get(key string) ([]byte, error) {
	return f(key)
}

type Group struct {
	name      string
	getter    Getter
	mainCache Cache
}

type NewCacheFunc func(maxBytes int64) Cache

type NewCacheBuilder struct {
	maxBytes    int64
	builderFunc NewCacheFunc
}

type GroupManage interface {
	Put(groupName string, key string, view ByteView) (ok bool)
	Get(groupName string, key string) (byteView ByteView, ok bool)
	ClearableVersionNum() (versions []int)
}

type groupContainer struct {
	mu           sync.RWMutex
	cacheBuilder NewCacheBuilder
	groups       map[string]*Group
	versionNums  map[int]int
}

func (g *groupContainer) Put(groupName string, key string, byteView ByteView) (ok bool) {
	g.mu.Lock()
	defer g.mu.Unlock()

	group, ok := g.groups[groupName]
	if !ok {
		group = &Group{
			name:      groupName,
			mainCache: g.cacheBuilder.builderFunc(g.cacheBuilder.maxBytes),
		}
		g.groups[groupName] = group
	}
	oldView, ok := group.mainCache.Put(key, byteView)
	if !ok {
		return false
	}

	// 修改各邻居节点版本对应的key数量
	num, ok := g.versionNums[oldView.Version()]
	if !ok {
		num = 0
	}
	g.versionNums[oldView.Version()] = common.Max(num-1, 0)
	num, ok = g.versionNums[byteView.Version()]
	if !ok {
		num = 0
	}
	g.versionNums[byteView.Version()] = num + 1
	fmt.Println("version:" + strconv.Itoa(byteView.Version()) + "," + strconv.Itoa(g.versionNums[byteView.Version()]))
	return true
}

func (g *groupContainer) Get(groupName string, key string) (byteView ByteView, ok bool) {
	g.mu.RLock()
	defer g.mu.RUnlock()

	group, ok := g.groups[groupName]
	if !ok {
		return
	}
	return group.mainCache.Get(key)
}

func (g *groupContainer) ClearableVersionNum() (versions []int) {
	g.mu.RLock()
	defer g.mu.RUnlock()
	versions = make([]int, 0)
	for version, num := range g.versionNums {
		if num == 0 {
			versions = append(versions, version)
		}
	}
	return versions
}

func NewGroupManage(newCacheBuilder NewCacheBuilder) GroupManage {
	return &groupContainer{
		cacheBuilder: newCacheBuilder,
		groups:       make(map[string]*Group),
		versionNums:  make(map[int]int),
	}
}
