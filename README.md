# Java Ray Tracing Engine

## Overview
A 3D ray tracing engine implemented in Java, designed to render complex scenes with realistic lighting and advanced visual effects.

The project focuses on building a full rendering pipeline from scratch, combining geometric modeling, light simulation, and performance optimization techniques.

---

## Preview

![Magical Underwater Scene](images/oceanUnderwaterScene.png)

---

## Key Features

- Full ray tracing pipeline implementation  
- Physically-inspired lighting model (reflection, refraction)  
- Support for complex geometric scenes  
- Transparent and reflective materials  
- Advanced sampling techniques for image quality improvement  

---

## Rendering Techniques

### Anti-Aliasing
Implemented supersampling (up to 81 rays per pixel) to reduce aliasing and improve edge smoothness.

### Adaptive Super Sampling
Adaptive subdivision of pixels based on color variance, reducing computation while maintaining high visual fidelity.

### Jittering
Randomized ray offsets to simulate natural visual effects such as soft shadows and smooth transitions.

### Refraction
Simulation of light behavior through transparent materials using refractive indices.

---

## Performance Optimization

### Multithreading
Parallelized rendering using Java ExecutorService, distributing workload across CPU cores.

### Results
- Reduced runtime by up to ~97% compared to naive rendering  
- Significant improvement from combining adaptive sampling and parallel execution  

---

## Architecture

The engine is designed with clear separation of responsibilities:

- Scene construction and object modeling  
- Ray generation and tracing  
- Lighting and shading calculations  
- Rendering pipeline orchestration  

The design emphasizes modularity and extensibility.

---

## Technologies

- Java  
- IntelliJ IDEA  
- Java Concurrency (ExecutorService)  

---

## Key Learnings

- Building a rendering engine from scratch  
- Applying performance optimizations to computationally intensive systems  
- Trade-offs between rendering quality and runtime  
- Practical use of multithreading  
- Designing scalable and modular systems  

---

## How to Run

1. Clone the repository  
2. Open in IntelliJ IDEA  
3. Run the main rendering class  
4. Output image will be generated  
