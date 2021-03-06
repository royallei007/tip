package com.xith.java3d.overlay;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.Raster;
import java.awt.event.*;
import java.awt.color.*;
import java.util.*;

/**
 * An overlay is rectangular texture which is aligned with the image plate giving
 * the illusion of an image drawn onto the canvas rather than in the scene. In an
 * accelerated environment the scene is drawn on the card and goes directly from
 * there onto the screen. In order to draw directly over the scene it must be 
 * rendered, then transfered to the system memory, drawn on, then tranfered back.
 * Each of these transfers take place on the bus between the card and the system
 * which is already busy with the normal operations of displaying everything not
 * 3d on the screen. Using a texture that is placed in front of the image plate
 * is a much cheaper operation. It just requires transering the data to the card
 * once (as oppoased to the whole scene back and forth across the bus for each
 * frame.) Also OverlayBase which is the default implementation of this interface
 * is double buffered so the transfer takes place in the background and once the
 * entire texture make it onto the card it is made active.
 *
 * Creating a new Overlay is very simple. Simply override paint(Graphics2D g) in
 * OverlayBase. If you have an overlay that is changing with time calling repaint()
 * will update it.
 *
 * As a note: Displaying large overlays use a *huge* amount of texture memory.
 * Every pixel in the overlay is represented by a set of integers (3 or 4 depending
 * on if the overlay has a transparency value (RGB vs RGBA.) For a 512x128 alpha
 * capable overlay that is (4 * 512 * 128) / 1024 = 256Kb of memory. Each texture
 * has 3 buffers though, one in system memory and two on the card so the actual 
 * memory used is 256Kb * 3 = 768Kb. A 512x512 overlay would take 3072Kb or 3Mb.
 *
 * Another thing to realize is that textures have to have dimesions that are powers
 * of two for optimization purposes. This means a 513x257 texture would require
 * 1024x512 of bounds to be allocated. Unlike most textures, overlays cannot
 * tolerate stretching and interpolation because of the fuzzyness that results.
 * The Xith overlay system breaks the requested overlaysize up into smaller pieces
 * so that extra texture memory is not wasted. This is hidden from the user who
 * simply sees a graphics context for the entire overlay. When the entire buffer
 * has been drawn though it is divided appropriately onto the SubOverlay's which
 * are positioned so as to look like one solid rectangle.
 *
 * Updates to the actual textures are done within a behavior within a frame. This
 * is very fast because all that is happening is that the buffers are getting
 * swapped. We have to be certain that we are not in the process of copying the big
 * buffer into the back buffer at the same time the behavior attempts to do a buffer
 * swap. This is handled by the overlay by not updating texture if we are in the
 * middle of drawing a new one. The drawback to this is that numerous updates per
 * second to the overlay could result in several updates not get immediately
 * reflected. But since the area is always completely redrawn this should not prove
 * to be an issue. Soon as we hit a frame where we are not updating the buffer then
 * it will be swapped.
 *
 * Remember, all you have to do to make translucent or non-square overlays is to use
 * the alpha channel.
 *
 * Copyright:  Copyright (c) 2000, 2001
 * Company:    Teseract Software, LLP
 * @author David Yazel
 * @author Will Holcomb
 */

public interface Overlay extends UpdatableEntity {
    /**
     * This mode prevents the background from being drawn
     */
    public static final int BACKGROUND_NONE = 0;
    
    /**
     * This mode copies the background image to the canvas before it is drawn
     */
    public static final int BACKGROUND_COPY = 1;

    /**
     * Places the overlay relative to the right side of the screen.
     */
    public final static int PLACE_RIGHT = 0;

    /**
     * Places the overlay relative to the left side of the screen.
     */
    public final static int PLACE_LEFT = 1;

    /**
     * Places the overlay relative to the center of the screen.
     */
    public final static int PLACE_CENTER = 3;

    /**
     * Places the overlay relative to the top of the screen.
     */
    public final static int PLACE_TOP = 0;

    /**
     * Places the overlay relative to the bottom of the screen.
     */
    public final static int PLACE_BOTTOM = 1;

    /**
     * When calling setRelativePosition with an int[2] array, this is the x component
     */
    public final static int X_PLACEMENT = 0;

    /**
     * When calling setRelativePosition with an int[2] array, this is the y component
     */
    public final static int Y_PLACEMENT = 1;

    /**
     * Returns the rectangular portion of the canvas that this overlay covers.
     */
    public Rectangle getBounds();

    /**
     * Returns the UpdateManager responsible for seeing that updates to the
     * Overlay only take place between frames.
     */
    public UpdateManager getUpdateManager();

    /**
     * Sets the UpdateManager
     */
    public void setUpdateManager(UpdateManager updateManager);

    /**
     * Sets the relative offset of the overlay. How this translates into
     * screen coordinates depends on the value of relativePosition()
     */
    public void setOffset(Dimension offset);

    /**
     * Sets the relative offset of the overlay. How this translates into
     * screen coordinates depends on the value of relativePosition()
     */
    public void setOffset(int width, int height);
    
    /**
     * Sets the relative position of the overlay on the screen.
     *
     * @param xType May be PLACE_LEFT, PLACE_RIGHT, or PLACE_CENTER
     * @param yType May be PLACE_TOP, PLACE_BOTTOM, or PLACE_CENTER
     */
    public void setRelativePosition(int xType, int yType);

    /**
     * Sets the relative position of the overlay on the screen using a 2 dimensional array.
     *
     * @param relativePosition[X_PLACEMENT] May be PLACE_LEFT, PLACE_RIGHT, or PLACE_CENTER
     * @param relativePosition[Y_PLACEMENT] May be PLACE_TOP, PLACE_BOTTOM, or PLACE_CENTER
     */
    public void setRelativePosition(int[] relativePositon);

    /**
     * Return the root of the Overlay so it can be added to
     * the scene graph.
     */
    public BranchGroup getRoot();
    
    /**
     * Sets whether drawing onto this Overlay is anialiased.
     */
    public void setAntialiased(boolean isAntialiased);

    /**
     * Returns whether drawing on this overlay is anti-aliased
     */
    public boolean isAntialiased();

    /**
     * Returns the canvas being drawn on
     */
    public Canvas3D getCanvas();

    /**
     * Changes the visibility of the Overlay.
     */
    public void setVisible(boolean visible);

    /**
     * Returns the visiblity of the Overlay.
     */
    public boolean isVisible();
    
    /**
     * Any changes that will affect the screen appearance should be made in here. The
     * UpdateManager is responsible for guearanteeing that this is executed in between
     * frmaes.
     */
    public void update();
}
