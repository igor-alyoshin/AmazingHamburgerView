package com.example.igor.amazingview;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by igor on 23.12.15.
 */
public class AnimateObservable<T> {

    PublishSubject<T> publishSubject;

    protected AnimateObservable(View v, ViewPropertyAnimator viewPropertyAnimator, PublishSubject<T> publishSubject) {
        this.v = v;
        this.viewPropertyAnimator = viewPropertyAnimator;
        this.publishSubject = publishSubject;
    }

    private ViewPropertyAnimator viewPropertyAnimator;
    private View v;

    public Subscription subscribe(Subscriber<T> subscriber) {
        return publishSubject.subscribe(subscriber);
    }

    public Subscription subscribe() {
        return publishSubject.subscribe();
    }

    public AnimateObservable rotateFrom(int rotation) {
        v.setRotation(rotation);
        return this;
    }

    public AnimateObservable rotateTo(int rotation) {
        viewPropertyAnimator.rotation(rotation);
        return this;
    }

    public AnimateObservable duration(int duration) {
        viewPropertyAnimator.setDuration(duration);
        return this;
    }

    public AnimateObservable alphaFrom(float alpha) {
        v.setAlpha(alpha);
        return this;
    }

    public AnimateObservable enabledFrom(boolean enabled) {
        v.setEnabled(enabled);
        return this;
    }

    public AnimateObservable enabledTo(final boolean enabled) {
        publishSubject.doOnCompleted(new Action0() {
            @Override
            public void call() {
                v.setEnabled(enabled);
            }
        }).subscribe();
        return this;
    }

    public AnimateObservable alphaTo(float alpha) {
        viewPropertyAnimator.alpha(alpha);
        return this;
    }

    public AnimateObservable translateFrom(int x, int y) {
        v.setTranslationX(x);
        v.setTranslationY(y);
        return this;
    }

    public AnimateObservable translateTo(int x, int y) {
        viewPropertyAnimator.translationX(x);
        viewPropertyAnimator.translationY(y);
        return this;
    }

    public AnimateObservable visibilityFrom(int visibility) {
        v.setVisibility(visibility);
        return this;
    }

    public AnimateObservable visibilityTo(final int visibility) {
        publishSubject.doOnCompleted(new Action0() {
            @Override
            public void call() {
                v.setVisibility(visibility);
            }
        }).subscribe();
        return this;
    }

    public static AnimateObservable animate(View v) {
        final PublishSubject<Void> publishSubject = PublishSubject.create();
        final ViewPropertyAnimator viewPropertyAnimator = v.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                publishSubject.onCompleted();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                publishSubject.onCompleted();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        return new AnimateObservable<>(v, viewPropertyAnimator, publishSubject);
    }
}
