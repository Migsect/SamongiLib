package net.samongi.SamongiLib.Vector;

import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SamIntVector {

    //--------------------------------------------------------------------------------------------------------------------//

    public static SamIntVector GetCenterVector = new SamIntVector(0, 0, 0);

    private static List<SamIntVector> s_sideVectors = null;
    public static List<SamIntVector> getSideVectors() {
        if (s_sideVectors != null) {
            return s_sideVectors;
        }

        List<SamIntVector> vectors = new ArrayList<SamIntVector>();
        vectors.add(new SamIntVector(1, 0, 0));
        vectors.add(new SamIntVector(-1, 0, 0));
        vectors.add(new SamIntVector(0, 1, 0));
        vectors.add(new SamIntVector(0, -1, 0));
        vectors.add(new SamIntVector(0, 0, 1));
        vectors.add(new SamIntVector(0, 0, -1));

        s_sideVectors = vectors;
        return vectors;
    }
    public static List<SamIntVector> getSideVectorsScrambled() {
        List<SamIntVector> scrambled = new ArrayList<>(getSideVectors());
        Collections.shuffle(scrambled);
        return scrambled;
    }

    private static List<SamIntVector> s_edgeVectors = null;
    public static List<SamIntVector> getEdgeVectors() {
        if (s_edgeVectors != null) {
            return s_edgeVectors;
        }

        List<SamIntVector> vectors = new ArrayList<SamIntVector>();
        vectors.add(new SamIntVector(1, 1, 0));
        vectors.add(new SamIntVector(-1, 1, 0));
        vectors.add(new SamIntVector(1, -1, 0));
        vectors.add(new SamIntVector(-1, -1, 0));

        vectors.add(new SamIntVector(0, 1, 1));
        vectors.add(new SamIntVector(0, -1, 1));
        vectors.add(new SamIntVector(0, 1, -1));
        vectors.add(new SamIntVector(0, -1, -1));

        vectors.add(new SamIntVector(1, 0, 1));
        vectors.add(new SamIntVector(1, 0, -1));
        vectors.add(new SamIntVector(-1, 0, 1));
        vectors.add(new SamIntVector(-1, 0, -1));

        s_edgeVectors = vectors;
        return vectors;
    }
    public static List<SamIntVector> getEdgeVectorsScrambled() {
        List<SamIntVector> scrambled = new ArrayList<>(getEdgeVectors());
        Collections.shuffle(scrambled);
        return scrambled;
    }

    private static List<SamIntVector> s_cornerVectors = null;
    public static List<SamIntVector> getCornerVectors() {
        if (s_cornerVectors != null) {
            return s_cornerVectors;
        }

        List<SamIntVector> vectors = new ArrayList<SamIntVector>();
        vectors.add(new SamIntVector(1, 1, 1));
        vectors.add(new SamIntVector(1, 1, -1));
        vectors.add(new SamIntVector(1, -1, 1));
        vectors.add(new SamIntVector(1, -1, -1));

        vectors.add(new SamIntVector(-1, 1, 1));
        vectors.add(new SamIntVector(-1, 1, -1));
        vectors.add(new SamIntVector(-1, -1, 1));
        vectors.add(new SamIntVector(-1, -1, -1));

        s_cornerVectors = vectors;
        return vectors;
    }
    public static List<SamIntVector> getCornerVectorsScrambled() {
        List<SamIntVector> scrambled = new ArrayList<>(getCornerVectors());
        Collections.shuffle(scrambled);
        return scrambled;
    }

    private static List<SamIntVector> s_surroundedVectors = null;
    /**
     * Returns all the surrounding vectors of the 0,0,0 vector.
     */
    public static List<SamIntVector> getSurroundingVectors() {
        if (s_surroundedVectors != null) {
            return s_surroundedVectors;
        }

        List<SamIntVector> vectors = new ArrayList<SamIntVector>();
        vectors.addAll(getSideVectors());
        vectors.addAll(getEdgeVectors());
        vectors.addAll(getCornerVectors());

        s_surroundedVectors = vectors;
        return vectors;
    }
    public static List<SamIntVector> getSurroundingVectorsScrambled() {
        List<SamIntVector> scrambled = new ArrayList<>(getSurroundingVectors());
        Collections.shuffle(scrambled);
        return scrambled;
    }

    /**
     * Scrambles the surrounding vectors based on where they are placed distance wise (sides, edges, corners), however
     * does not scramble entirely randomly like getSurroundingVectorsScrambled
     *
     * @return
     */
    public static List<SamIntVector> getSurroundingVectorsSemiScrambled() {
        List<SamIntVector> vectors = new ArrayList<SamIntVector>();
        vectors.addAll(getSideVectorsScrambled());
        vectors.addAll(getEdgeVectorsScrambled());
        vectors.addAll(getCornerVectorsScrambled());
        return vectors;
    }

    //--------------------------------------------------------------------------------------------------------------------//
    private int m_x = 0;
    private int m_y = 0;
    private int m_z = 0;

    //--------------------------------------------------------------------------------------------------------------------//
    public SamIntVector(@Nonnull Location location) {
        m_x = location.getBlockX();
        m_y = location.getBlockY();
        m_z = location.getBlockZ();
    }

    public SamIntVector(int x, int y, int z) {
        m_x = x;
        m_y = y;
        m_z = z;
    }

    public int X() {
        return m_x;
    }
    public int Y() {
        return m_y;
    }
    public int Z() {
        return m_z;
    }

    //--------------------------------------------------------------------------------------------------------------------//
    public boolean equals(SamIntVector other) {
        return other.m_x == this.m_x && other.m_y == this.m_y && other.m_z == this.m_z;
    }

    @Override public boolean equals(Object object) {
        if (object instanceof SamIntVector) {
            return equals((SamIntVector) object);
        }
        return false;
    }

    //--------------------------------------------------------------------------------------------------------------------//
    public SamIntVector add(SamIntVector other) {
        return new SamIntVector(this.m_x + other.m_x, this.m_y + other.m_y, this.m_z + other.m_z);
    }
    public SamIntVector add(int value) {
        return new SamIntVector(this.m_x + value, this.m_y + value, this.m_z + value);
    }
    public SamIntVector multiply(int value) {
        return new SamIntVector(this.m_x * value, this.m_y * value, this.m_z * value);
    }

    public Location toLocation(@Nonnull World world) {
        return new Location(world, m_x, m_y, m_z);
    }

    public double getDistanceSquared(@Nonnull SamIntVector other) {
        return (Math.pow(other.X() - X(), 2) + Math.pow(other.Y() - Y(), 2) + Math.pow(other.Z() - Z(), 2));
    }

    public double getDistance(@Nonnull SamIntVector other) {
        return Math.sqrt(getDistanceSquared(other));
    }

    //--------------------------------------------------------------------------------------------------------------------//
    /**
     * Rotates about the X axis.
     * <p>
     * This will result in an int vector meaning that the resulting
     * positions of the rotation will be rounded to the nearest int.
     *
     * @param rotation The amount of rotation
     * @return The rotated int vector.
     */
    public SamIntVector rotateX(double rotation) {
        SamVector doubleVector = new SamVector(m_x, m_y, m_z);
        SamVector rotatedVector = doubleVector.rotateX(rotation);
        return new SamIntVector(rotatedVector.getBlockX(), rotatedVector.getBlockY(), rotatedVector.getBlockZ());
    }

    /**
     * Rotates about the Y axis.
     * <p>
     * This will result in an int vector meaning that the resulting
     * positions of the rotation will be rounded to the nearest int.
     *
     * @param rotation
     * @return The rotated int vector.
     */
    public SamIntVector rotateY(double rotation) {
        SamVector doubleVector = new SamVector(m_x, m_y, m_z);
        SamVector rotatedVector = doubleVector.rotateY(rotation);
        return new SamIntVector(rotatedVector.getBlockX(), rotatedVector.getBlockY(), rotatedVector.getBlockZ());
    }
    /**
     * Rotates about the Z axis.
     * <p>
     * This will result in an int vector meaning that the resulting
     * positions of the rotation will be rounded to the nearest int.
     *
     * @param rotation
     * @return The rotated int vector.
     */
    public SamIntVector rotateZ(double rotation) {
        SamVector doubleVector = new SamVector(m_x, m_y, m_z);
        SamVector rotatedVector = doubleVector.rotateZ(rotation);
        return new SamIntVector(rotatedVector.getBlockX(), rotatedVector.getBlockY(), rotatedVector.getBlockZ());
    }

}
