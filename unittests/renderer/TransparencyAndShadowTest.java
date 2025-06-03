package renderer;

import lighting.*;
import primitives.*;
import scene.Scene;
import geometries.*;
import org.junit.jupiter.api.Test;

/**
 * A unit test scene showing transparency and shadow with 4 shapes.
 */
public class TransparencyAndShadowTest {

    @Test
    public void transparencyAndShadowTest() {
        Scene scene = new Scene("Transparency and Shadow Test");
        scene.setBackground(new Color(20, 20, 80)) // Dark blue background
                .setAmbientLight(new AmbientLight(new Color(40, 40, 40))); // Soft light everywhere

        // Blue see-through sphere in the middle
        Geometry transparentSphere = new Sphere(new Point(0, 0, -100), 50d)
                .setEmission(new Color(60, 120, 240))
                .setMaterial(new Material()
                        .setKD(0.3) // diffuse
                        .setKS(0.4) // shiny spot
                        .setShininess(120) // how sharp the shiny spot is
                        .setKT(0.7)); // transparency

        // Red solid sphere on the right (makes shadows)
        Geometry opaqueSphere = new Sphere(new Point(70, 0, -120), 30d)
                .setEmission(new Color(255, 80, 80))
                .setMaterial(new Material()
                        .setKD(0.6)
                        .setKS(0.4)
                        .setShininess(120));

        // Gray triangle at the bottom (mirror floor)
        Geometry floor = new Triangle(
                new Point(-200, -50, -200),
                new Point(200, -50, -200),
                new Point(0, -50, 100))
                .setEmission(new Color(70, 70, 70))
                .setMaterial(new Material().setKR(0.7)); // reflection

        // Purple glass triangle (see-through panel)
        Geometry glassPanel = new Triangle(
                new Point(-80, 50, -120),
                new Point(0, 100, -120),
                new Point(0, -40, -60))
                .setEmission(new Color(180, 160, 255))
                .setMaterial(new Material().setKT(0.6).setShininess(90));

        scene.geometries.add(transparentSphere, opaqueSphere, floor, glassPanel);

        // Bright light from the top left (makes shadows)
        scene.lights.add(
                new SpotLight(new Color(1200, 800, 800), new Point(-80, 90, 50), new Vector(1.2, -1, -2))
                        .setKl(0.0002).setKq(0.00005));

        // Soft light from top right to help brighten things
        scene.lights.add(
                new PointLight(new Color(300, 200, 200), new Point(100, 120, 150))
                        .setKl(0.001).setKq(0.0004));

        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 200))
                .setDirection(new Point(0, 0, -100))
                .setVpSize(300, 300)
                .setVpDistance(200)
                .setResolution(600, 600)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();

        camera.renderImage();
        camera.writeToImage("transparencyShadowTest");
    }


    }

