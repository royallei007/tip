package com.xith.java3d.overlay;

import com.xith.java3d.overlay.*;
import javax.media.j3d.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;
import java.awt.font.*;
import javax.vecmath.*;

public class LabelOverlay extends OverlayBase {
    private AttributedString text;

    private int visibleLength = 0;
    private int textLength = 0;
    private int typingDelta = 0;

    private Font font;
    private Color color;
    TypingBehavior typer;
    boolean typing = false;
    
    public LabelOverlay( Canvas3D canvas, Rectangle space ) {
	this(canvas, space, "");
    }
    
    public LabelOverlay( Canvas3D canvas, Rectangle space, String text ) {
	this(canvas, space, text, new Font("Helvetica", Font.BOLD, 14), Color.red, null);
    }
    
    public LabelOverlay( Canvas3D canvas, Rectangle space, String text, Font font, Color color ) {
	this(canvas, space, text, font, color, null);
    }
    
    public LabelOverlay( Canvas3D canvas, Rectangle space,
			 String text, Font font, Color color,
			 UpdateManager manager ) {
	super(canvas, space, manager);
	this.font = font;
	this.color = color;
	setText(text);
    }
    
    public LabelOverlay( Canvas3D canvas, Rectangle space, AttributedString text ) {
	this(canvas, space, text, (UpdateManager)null);
    }
    
    public LabelOverlay( Canvas3D canvas, Rectangle space,
			 AttributedString text, UpdateManager manager ) {
	super(canvas, space, manager);
	setText(text);
    }

    protected void initialize() {
	typer = new TypingBehavior();
	typer.setSchedulingBounds(new BoundingSphere());
	getRoot().addChild(typer);
	setBackgroundColor(new Color(0, 0, 0, 0));
    }   

    public void paint(Graphics2D g) {
	if(text != null) {
	    synchronized(text) {
		AttributedCharacterIterator characterIterator = text.getIterator(null, 0, visibleLength);
		if(characterIterator.getEndIndex() > 0) {
		    TextLayout textLayout = new TextLayout(characterIterator, g.getFontRenderContext());
		    textLayout.draw(g, 0, 3 * getBounds().height / 4);
		}
	    }
	}
    }

    public void setColor(Color color) {
	if(!this.color.equals(color)) {
	    this.color = color;
	    if(text != null) {
		text.addAttribute(TextAttribute.FOREGROUND, color);
		repaint();
	    }
	}
    }

    public void setFont(Font font) {
	if(!this.font.equals(font)) {
	    this.font = font;
	    if(text != null) {
		text.addAttribute(TextAttribute.FONT, font);
		repaint();
	    }
	}
    }

    public void setText(String text) {
	setText(text, text.length());
    }
    
    public void setText(String text, int typingDelta) {
	setText(createAttributedString(text, font, color), typingDelta);
    }

    public void setText(AttributedString text) {
	if(text != null) {
	    setText(text, text.getIterator().getEndIndex() + 1);
	}
    }

    public void setText(AttributedString text, int typingDelta) {
	if(this.text != text) {
	    this.text = text;
	    textLength = text.getIterator().getEndIndex();
	    visibleLength = 0;
	    this.typingDelta = typingDelta;
	    typer.type();
	}
    }

    public void backspace(int numCharacters) {
	visibleLength = Math.max(0, visibleLength - numCharacters);
    }

    Object typingBlock = new Object();

    protected void updateTyping() {
	visibleLength = Math.min(textLength, visibleLength + typingDelta);
	repaint();
	typing = visibleLength < textLength;
	if (!typing) {
	    synchronized (typingBlock) {
		typingBlock.notifyAll();
	    }
	}
    }

    public void waitForTyping() {
	while (typing) {
	    synchronized (typingBlock) {
		try {
		    typingBlock.wait();
		} catch(InterruptedException e) {
		}
	    }
	}
    }

    public void setTypingSpeed(long delayInMilliseconds) {
	typer.setTypingSpeed(delayInMilliseconds);
    }

    public int getVisibleLength() {
	return visibleLength;
    }

    public boolean isTyping() {
	return typing;
    }

    public static AttributedString createAttributedString(String text, Font font, Color color) {
	AttributedString attributedString = null;
	if(text.length() > 0) { 
	    attributedString = new AttributedString(text);
	    attributedString.addAttribute(TextAttribute.FONT, font);
	    attributedString.addAttribute(TextAttribute.FOREGROUND, color);
	}
	return attributedString;
    }
    
    class TypingBehavior extends Behavior {
	int START_TYPING = 1;
	WakeupOnBehaviorPost postWakeup = new WakeupOnBehaviorPost(this, START_TYPING);
	WakeupOnElapsedTime timeWakeup = new WakeupOnElapsedTime(100);

	public void initialize() {
	    wakeupOn(new WakeupOnActivation());
	}

	public void type() {
	    postId(START_TYPING);
	    typing = true;
	}

	public void processStimulus(Enumeration criteria) {
	    updateTyping();
	    if (typing) {
		wakeupOn(timeWakeup);
	    } else {
		wakeupOn(postWakeup);
	    }
	}

	public void setTypingSpeed(long speed) {
	    timeWakeup = new WakeupOnElapsedTime(speed);
	}
    }
}
