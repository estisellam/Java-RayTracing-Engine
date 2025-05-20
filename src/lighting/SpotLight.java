package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * Class representing a spotlight in a 3D scene.
 * A SpotLight is a PointLight with a direction.
 */
public class SpotLight extends PointLight {
    /**
     * Vector representing the direction of the spotlight (normalized).
     */
    private final Vector direction;
    /**
     * Narrow beam angle in degrees
     */
    private int narrowBeam = 1;

    /**
     * Constructor for SpotLight.
     *
     * @param direction the direction of the light
     * @param intensity the intensity (color) of the light
     * @param position  the position of the light source

     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Calculates the intensity at point p considering the spotlight direction.
     *
     * @param p the point in space
     * @return the color intensity of the light at point p
     */
    @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p).normalize();
        Vector dir = direction.normalize();

        double projection = alignZero(dir.dotProduct(l));
        if (projection <= 0) {
            return Color.BLACK;
        }
        projection = Math.min(projection, 1);

        double focusFactor = (narrowBeam == 1) ? projection : Math.pow(projection, narrowBeam);

        Color baseIntensity = super.getIntensity(p);
        return baseIntensity.scale(Math.min(focusFactor, 1));
    }
    /**
     * Returns a normalized direction vector from the light to point p.
     *
     * @param p the point in space
     * @return normalized vector from light to point
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Sets the constant attenuation factor.
     * @param kC the constant attenuation factor
     * @return this SpotLight instance
     */
    public SpotLight setKC(double kC) {
        super.setKc(kC);
        return this;
    }
    /**
     * Sets the linear attenuation factor.
     * @param kL the linear attenuation factor
     * @return this SpotLight instance
     */
    public SpotLight setKL(double kL) {
        super.setKl(kL);
        return this;
    }
    /**
     * Sets the quadratic attenuation factor.
     * @param kQ the quadratic attenuation factor
     * @return this SpotLight instance
     */
    public SpotLight setKQ(double kQ) {
        super.setKq(kQ);
        return this;
    }

    /**
     * Sets the narrow beam angle.
     * @param narrowBeam the narrow beam angle in degrees
     * @return this SpotLight instance
     */
    @Override
    public SpotLight setNarrowBeam(int narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

}