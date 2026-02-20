

# Client-Side Load Balancer with Softmax Action Selection

## Overview

Bu projede, K adet sunucudan oluşan bir cluster'a gelen istekleri dağıtmak için **Softmax Action Selection** algoritmasına dayalı bir **client-side load balancer** implement edilmiştir.

Amaç:

* Ortalama latency değerini minimize etmek
* Dinamik (non-stationary) sunucu performanslarına adapte olabilen bir algoritma geliştirmek

Sistem simülasyon tabanlıdır.

---

# Problem

Cluster yapısında:

* K adet server vardır
* Server latency değerleri zamanla değişebilir
* Latency ölçümleri gürültülüdür (noisy)
* Optimal server önceden bilinmez

Bu nedenle klasik algoritmalar (Random, Round-Robin) yerine **öğrenebilen bir seçim mekanizması** kullanılmaktadır.

---

# Approach

Softmax Action Selection algoritması kullanılmıştır.

Her server için bir tahmin değeri tutulur:

```
Q[i] → server i için beklenen reward
```

Seçim olasılığı:

```
P(i) = exp(Q[i] / T) / Σ exp(Q[j] / T)
```

Burada:

* T → temperature (exploration kontrolü)
* Q → öğrenilen değer

Reward fonksiyonu:

```
reward = 1 / latency
```

Q güncelleme kuralı:

```
Q[i] = Q[i] + α (reward - Q[i])
```

---

# Project Structure

```
src
 ├── Main.java
 ├── Server.java
 ├── SoftmaxLoadBalancer.java
 ├── RandomLoadBalancer.java
 └── Simulation.java
```

---

# Components

## Server

Server latency üretir.

Latency modeli:

```
latency = baseLatency + noise
```

Noise:

```
Gaussian distribution
```

Environment değişimi simülasyonu desteklenir.

---

## SoftmaxLoadBalancer

Görevleri:

* Q değerlerini tutmak
* Olasılık dağılımı hesaplamak
* Server seçmek
* Learning update yapmak

Parametreler:

```
temperature (T)
learning rate (alpha)
```

---

## RandomLoadBalancer

Baseline algoritma.

Özellikler:

* Öğrenme yok
* Rastgele server seçimi

Softmax ile karşılaştırma için kullanılır.

---

## Simulation

Simülasyon süreci:

1. Server'lar oluşturulur
2. Load balancer başlatılır
3. Request loop çalıştırılır
4. Latency ölçülür
5. Reward hesaplanır
6. Model güncellenir

Orta noktada environment değişimi yapılır:

```
non-stationary system
```

---

# Running the Project

```
javac *.java
java Main
```

veya IntelliJ IDEA üzerinden:

```
Run Main.java
```

---

# Output

Simülasyon sonunda:

* Average latency
* Algorithm comparison

gösterilir.

Expected behavior:

```
Softmax < Random (average latency)
```

---

# Key Concepts

* Client-side load balancing
* Reinforcement learning
* Exploration vs exploitation
* Non-stationary systems
* Probabilistic action selection

---

# Possible Extensions

* UCB algorithm
* Thompson Sampling
* Adaptive temperature
* Real network latency modeling
* Visualization

---

