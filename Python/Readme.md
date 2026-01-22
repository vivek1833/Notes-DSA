## Array (Python list)

```python
# Basic initialization
int_array = [0] * 20
int_array = [1,2,3,4,5,6,7,8,9,10]

# Two dimensional array
two_d_arr = [[0]*10 for _ in range(10)]

# Fill array
arr = [10] * len(arr)

# Sort
arr.sort()                  # ASC
arr.sort(reverse=True)      # DSC
# Or
arr = sorted(arr)
arr = sorted(arr, reverse=True)

# Reverse array
arr.reverse()
# Or
arr = arr[::-1]

# Reverse string array
arr = ["Hello", "World"]
reversed_array = arr[::-1]
```

---

## List (ArrayList)

```python
# Basic initialization
lst = []
numbers = [60, 25, 12]

# From array
arr = [2,3,4,4]
lst = list(arr)

# Two dimensional list
countries = [[] for _ in range(100)]

# Size
len(lst)

# Add
lst.append(10)
lst.insert(1, 20)
lst.extend(numbers)

# Get
lst[0]

# Remove
lst.pop(0)          # by index
lst.remove(10)      # by value
lst.clear()

10 in lst
lst.index(10) if 10 in lst else -1
not lst             # isEmpty

str(lst)
lst.copy()

# Sorting
lst.sort()
lst.sort(reverse=True)
```

---

## Linked List (collections.deque)

```python
from collections import deque

linked_list = deque()

len(linked_list)

# Add
linked_list.append("a")
linked_list.appendleft("b")

# Get
linked_list[0]
linked_list[0]      # first
linked_list[-1]     # last

# Remove
linked_list.popleft()
linked_list.pop()

list(linked_list)
```

---

## Stack (list)

```python
stack = []

not stack           # isEmpty

stack.append(10)    # push
stack[-1]           # peek
stack.pop()         # pop

len(stack)
stack.copy()
str(stack)
```

---

## Queue (collections.deque)

```python
from collections import deque

queue = deque()

not queue

queue.append(10)    # offer/add
queue[0]            # peek

queue.popleft()     # poll/remove

len(queue)
list(queue)
```

---

## Priority Queue (heapq)

```python
import heapq

# ASC
pqueue = []
heapq.heappush(pqueue, 10)
heapq.heappop(pqueue)

# DSC
pqueue = []
heapq.heappush(pqueue, -10)
-max(heapq.heappop(pqueue))

len(pqueue)
not pqueue
```

---

## Dictionary (HashMap)

```python
# Basic initialization
mp = {}
mp2 = {1: ["a", "b"]}

len(mp)
not mp
"key" in mp

# Get
mp.get("key")
mp.get("key", "default")

# Put
mp["key"] = "value"

# Remove
mp.pop("key", None)

# Loop
for k, v in mp.items():
    pass

# Keys / values
list(mp.keys())
list(mp.values())

# Array of maps
maps = [{} for _ in range(26)]
for i in range(26):
    for j in range(10):
        maps[i][f"key{j}"] = f"value{j}"
```

---

## Ordered Map (TreeMap equivalent)

```python
# Python dict preserves insertion order
from collections import OrderedDict

tree_map = dict(sorted({"b":2, "a":1}.items()))
tree_map_desc = dict(sorted(tree_map.items(), reverse=True))

tree_map.get("a")
tree_map.get("a", 0)

tree_map["c"] = 3
tree_map.pop("a", None)

first_key = next(iter(tree_map))
last_key = next(reversed(tree_map))
```

---

## Set (HashSet)

```python
st = set()

len(st)
st.add("a")
"a" in st
st.remove("a")      # KeyError if missing
st.discard("a")     # safe

st.clear()
not st

for s in st:
    pass
```

---

## Sorted Set (TreeSet)

```python
# No native TreeSet. Use sorted set behavior.
st = set()

st.add(3)
st.add(1)

sorted_st = sorted(st)
sorted_desc = sorted(st, reverse=True)

min(st)
max(st)

# pollFirst / pollLast
first = min(st); st.remove(first)
last = max(st); st.remove(last)
```

---

## StringBuilder & String

```python
# Python strings are immutable
sb = []
sb.append("Hello")
sb.append("World")
result = "".join(sb)

len(result)
result[::-1]

# Add
sb.append("c")
sb.append("String")
sb.append(str(123))

# Access
result[0]
result.find("lo")
result == "HelloWorld"

# Split
arr = result.split(":")

# Int to string
s = str(200)

# String to int
value = int("200")

# Char array to string
ch_arr = ['H','e','l','l','o']
s = "".join(ch_arr)

# Char array to builder
sb = []
for c in ch_arr:
    sb.append(c)
final = "".join(sb)

# Frequency check
frequency = [0]*26
for ch in "abcba":
    idx = ord(ch) - ord('a')
    frequency[idx] += 1
    if frequency[idx] == 2:
        found = True
```
