
# Java Ray Tracing Engine

A 3D rendering engine built in **Java**, featuring realistic lighting, advanced sampling techniques, and multithreaded rendering.

Developed as an academic software engineering project, focusing on **computational geometry, algorithms, concurrency, and performance optimization**.

## Render Preview

![Underwater Scene](images/oceanUnderwaterScene.png)

## Key Features

- **Ray Tracing:** Ray–geometry intersections and realistic lighting calculations.
- **Reflection & Refraction:** Support for reflective and transparent materials.
- **Anti-Aliasing:** Supersampling with up to 81 rays per pixel.
- **Adaptive Super Sampling:** Reduces redundant ray calculations while preserving visual quality.
- **Soft Shadows & Jittering:** Improved visual realism through randomized sampling.
- **Multithreading:** Parallel rendering using Java's `ExecutorService`.

## Architecture

The engine follows a modular rendering pipeline:

```text
Scene & Camera
      ↓
Ray Generation
      ↓
Geometry Intersections
      ↓
Lighting & Materials
      ↓
Pixel Sampling
      ↓
Rendered Image
```

## Performance Optimization

Implemented two complementary optimization techniques:

- **Parallel Rendering:** Distributes rendering tasks across CPU cores using `ExecutorService`.
- **Adaptive Sampling:** Concentrates ray calculations in visually complex regions.

Performance can be evaluated by comparing rendering times for identical scenes and resolutions, using equivalent image-quality settings.

*Note: Quantified speedup results are pending reproducible benchmark measurements.*

## Tech Stack

**Java · OOP · ExecutorService · Computational Geometry · Multithreading**

## Getting Started

```bash
git clone https://github.com/estisellam/Java-RayTracing-Engine.git
cd Java-RayTracing-Engine
```

Open the project in **IntelliJ IDEA**, configure a compatible JDK, and run a scene-rendering entry point or test.

*The exact entry point and JDK version are pending local verification.*

## Engineering Focus

- Designing modular rendering components.
- Balancing image quality and computational cost.
- Applying parallel programming to CPU-intensive workloads.
- Optimizing algorithms through adaptive computation.
