package org.nasa.mars.rovers.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * The "CommonUtil" class provides a set of utility methods that can be used in various parts of an application.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtil {
    /**
     * Used delimiter to read informations about plateau, movable and instructions

     * Example:
     * 5 5
     * 1 2 N
     */
    public static final String delimiter = " ";

    /**
     * Used quitter to exit the command line application and show the final movable status
     */
    public static final String quitter = "q";
}
