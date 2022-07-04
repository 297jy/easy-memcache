package cache

import (
	"container/list"
	"fmt"
	"strconv"
	"sync"
)

type lruCache struct {
	maxBytes int64
	nbytes   int64
	ll       *list.List
	mu       sync.RWMutex
	cache    map[string]*list.Element
}

func (c *lruCache) Put(key string, value ByteView) (oldValue ByteView, ok bool) {
	c.mu.Lock()
	defer c.mu.Unlock()

	if ele, ok := c.cache[key]; ok {
		c.ll.MoveToFront(ele)
		kv := ele.Value.(*entry)
		c.nbytes += int64(value.Len()) - int64(kv.value.Len())
		oldValue = kv.value
		kv.value = value
	} else {
		ele := c.ll.PushFront(&entry{key, value})
		c.cache[key] = ele
		c.nbytes += int64(len(key)) + int64(value.Len())
	}

	for c.maxBytes != 0 && c.maxBytes < c.nbytes {
		c.Eliminate()
	}
	return oldValue, true
}

func (c *lruCache) Eliminate() {
	ele := c.ll.Back()
	if ele != nil {
		c.ll.Remove(ele)
		kv := ele.Value.(*entry)
		delete(c.cache, kv.key)
		freeBytes := int64(len(kv.key)) + int64(kv.value.Len())
		c.nbytes -= freeBytes

		fmt.Println("淘汰的key为:" + kv.key + "," + strconv.FormatInt(freeBytes, 10))
	}
}

func (c *lruCache) Get(key string) (value ByteView, ok bool) {

	c.mu.RLock()
	defer c.mu.RUnlock()

	if ele, ok := c.cache[key]; ok {
		c.ll.MoveToFront(ele)
		kv := ele.Value.(*entry)
		return kv.value, true
	}
	return
}

func NewLruCache(maxBytes int64) Cache {
	return &lruCache{
		maxBytes: maxBytes,
		nbytes:   0,
		ll:       list.New(),
		cache:    make(map[string]*list.Element),
	}
}
