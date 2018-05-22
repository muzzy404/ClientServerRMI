
# RMI Server-Client test
## 1 to 9 threads (results table and chart)
| Threads | Requests per second |
| ------- | ------------------- |
| 1       | 20063,799           |
| 2       | 35707,506           |
| 3       | 44730,953           |
| 4       | 48479,960           |
| 5       | 49420,472           |
| 6       | 49859,833           |
| 7       | 51131,707           |
| 8       | 50978,651           |
| 9       | 50895,702           |


![1 to 9 threads](https://cldup.com/OdTYu7WsSy-3000x3000.png)

The optimal variant of threads number is the CPU cores number - **4 threads**. This is clearly visible on the chart.

## 10 to 100 threads (results table and chart)
| Threads | Requests per second |
| ------- | ------------------- |
| 10      | 49442,207           |
| 20      | 48734,848           |
| 30      | 47949,932           |
| 40      | 47396,224           |
| 50      | 47806,379           |
| 60      | 46854,200           |
| 70      | 46585,500           |
| 80      | 46889,165           |
| 90      | 46411,179           |
| 100     | 46947,771           |

![10 to 100 threads](https://cldup.com/ZJBbA3jbsp-3000x3000.png)
![10 to 100 threads, zoom](https://cldup.com/TQ3iJbHSbE-3000x3000.png)

## Results analysis
The number of requests per second increases pretty good until reaching the number of CPU cores (4). Results looking such way because of physical limit for multithreaded applications. When the number of threads is bigger than CPU cores number a computer cannot use the same number of real threats as the application wants to get.

## CPU info
```sh
~$ lscpu
Architecture:          x86_64
CPU op-mode(s):        32-bit, 64-bit
Byte Order:            Little Endian
CPU(s):                4
On-line CPU(s) list:   0-3
Thread(s) per core:    2
Core(s) per socket:    2
Socket(s):             1
NUMA node(s):          1
Vendor ID:             GenuineIntel
CPU family:            6
Model:                 58
Model name:            Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz
Stepping:              9
CPU MHz:               1232.156
CPU max MHz:           3300,0000
CPU min MHz:           1200,0000
BogoMIPS:              5182.93
Virtualization:        VT-x
L1d cache:             32K
L1i cache:             32K
L2 cache:              256K
L3 cache:              3072K
NUMA node0 CPU(s):     0-3
```
This CPU has 2 cores but the hyper-threading technology increases this value to 4.
