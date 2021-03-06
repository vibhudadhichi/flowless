package com.zhuinden.flowless_viewpager_example.extracted;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import flowless.Direction;
import flowless.Traversal;

/**
 * Created by Zhuinden on 2016.12.03..
 */

class DispatcherUtils {
    public static void addViewToGroupForKey(Direction direction, View view, ViewGroup root, LayoutKey animatedKey) {
        if(animatedKey.animation() != null && !animatedKey.animation().showChildOnTopWhenAdded(direction)) {
            root.addView(view, 0);
        } else {
            root.addView(view);
        }
    }

    public static Animator createAnimatorForViews(LayoutKey animatedKey, View previousView, View newView, Direction direction) {
        if(previousView == null) {
            return null;
        }
        if(animatedKey.animation() != null) {
            return animatedKey.animation().createAnimation(previousView, newView, direction);
        }
        return null;
    }

    public static View createViewFromKey(Traversal traversal, LayoutKey newKey, ViewGroup root, Context baseContext) {
        Context internalContext = DispatcherUtils.createContextForKey(traversal, newKey, baseContext);
        LayoutInflater layoutInflater = LayoutInflater.from(internalContext);
        final View newView = layoutInflater.inflate(newKey.layout(), root, false);
        return newView;
    }


    public static Context createContextForKey(Traversal traversal, LayoutKey newKey, Context baseContext) {
        return traversal.createContext(newKey, baseContext);
    }

    public static LayoutKey selectAnimatedKey(Direction direction, LayoutKey previousKey, LayoutKey newKey) {
        return direction == Direction.BACKWARD ? (previousKey != null ? previousKey : newKey) : newKey;
    }

}
