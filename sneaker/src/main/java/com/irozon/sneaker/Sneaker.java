package com.irozon.sneaker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.irozon.sneaker.interfaces.OnSneakerClickListener;
import com.irozon.sneaker.interfaces.OnSneakerDismissListener;
import com.irozon.sneaker.widget.RoundedImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Hammad Akram on 5/24/2017.
 */

public class Sneaker implements View.OnClickListener {

    private static final int DEFAULT_VALUE = -100000;
    private static int mIcon = DEFAULT_VALUE;
    private static Drawable mIconDrawable = null;
    private static int mBackgroundColor = DEFAULT_VALUE;
    private static int mIconColorFilterColor = DEFAULT_VALUE;
    private static int mIconSize = 24;
    private static String mMessage = "";
    private static int mMessageColor = DEFAULT_VALUE;
    private static boolean mAutoHide = true;
    private static int mDuration = 3000;
    private static WeakReference<LinearLayout> layoutWeakReference;
    private static WeakReference<Activity> contextWeakReference;
    private static boolean mIsCircular = false;
    private static boolean mDismissOnTouch = false;
    private static OnSneakerClickListener mListener = null;
    private static OnSneakerDismissListener mDismissListener = null;
    private static Typeface mTypeFace = null;
    private static int mCornerRadius = DEFAULT_VALUE;
    private static int mMargin = DEFAULT_VALUE;
    private static int mTextSize = 14;

    /**
     * Constructor
     *
     * @param activity
     */
    private Sneaker(Activity activity) {
        contextWeakReference = new WeakReference<>(activity);
    }

    /**
     * Create Sneaker with activity reference
     *
     * @param activity
     * @return
     */
    public static Sneaker with(Activity activity) {
        Sneaker sneaker = new Sneaker(activity);
        setDefault();
        return sneaker;
    }

    /**
     * Hides the sneaker
     */
    public static void hide() {
        if (getLayout() != null) {
            getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
            if (mDismissListener != null) {
                mDismissListener.onDismiss();
            }
            getActivityDecorView().removeView(getLayout());
        }
    }

    /**
     * Return activity parent view
     *
     * @return
     */
    private static ViewGroup getActivityDecorView() {
        ViewGroup decorView = null;

        decorView = (ViewGroup) ((Activity) getContext()).getWindow().getDecorView();

        return decorView;
    }

    /**
     * Sets the default values to the sneaker
     */
    private static void setDefault() {
        mIcon = DEFAULT_VALUE;
        mIconDrawable = null;
        mIconColorFilterColor = DEFAULT_VALUE;
        mIconSize = 24;
        mTextSize = 14;
        mBackgroundColor = DEFAULT_VALUE;
        mAutoHide = true;
        mMessageColor = DEFAULT_VALUE;
        mIsCircular = false;
        mListener = null;
        mTypeFace = null;
        mCornerRadius = DEFAULT_VALUE;
        mMargin = DEFAULT_VALUE;
    }

    /**
     * Return activity weak reference
     *
     * @return
     */
    private static Context getContext() {
        return contextWeakReference.get();
    }

    /**
     * Returns sneaker main layout weak reference
     *
     * @return
     */
    private static View getLayout() {
        return layoutWeakReference.get();
    }

    /**
     * Sets the message to sneaker
     *
     * @param message String value of message
     * @return
     */
    public Sneaker setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * Sets the message to sneaker with color
     *
     * @param message String value of message
     * @param color   Color resource for message text
     * @return
     */
    public Sneaker setMessage(String message, int color) {
        mMessage = message;
        if (getContext() != null) {
            try {
                mMessageColor = ContextCompat.getColor(getContext(), color);
            } catch (Exception e) {
                mMessageColor = color;
            }
        }
        return this;
    }

    public Sneaker setTextSize(int testSize) {
        mTextSize = testSize;
        return this;
    }

    /**
     * Sets the icon to sneaker
     *
     * @param icon Icon resource for sneaker
     * @return
     */
    public Sneaker setIcon(int icon) {
        mIconDrawable = null;
        mIcon = icon;
        return this;
    }

    /**
     * Sets the icon to sneaker
     *
     * @param icon Icon drawable for sneaker
     * @return
     */
    public Sneaker setIcon(Drawable icon) {
        mIcon = DEFAULT_VALUE;
        mIconDrawable = icon;
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option
     *
     * @param icon
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(int icon, boolean isCircular) {
        mIconDrawable = null;
        mIcon = icon;
        mIsCircular = isCircular;
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option
     *
     * @param icon
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(Drawable icon, boolean isCircular) {
        mIcon = DEFAULT_VALUE;
        mIconDrawable = icon;
        mIsCircular = isCircular;
        return this;
    }

    public Sneaker setIcon(int icon, int tintColor) {
        mIconDrawable = null;
        mIcon = icon;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    public Sneaker setIcon(Drawable icon, int tintColor) {
        mIcon = DEFAULT_VALUE;
        mIconDrawable = icon;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option and icon tint
     *
     * @param icon
     * @param tintColor  Icon tint color
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(int icon, int tintColor, boolean isCircular) {
        mIconDrawable = null;
        mIcon = icon;
        mIsCircular = isCircular;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option and icon tint
     *
     * @param icon
     * @param tintColor  Icon tint color
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(Drawable icon, int tintColor, boolean isCircular) {
        mIcon = DEFAULT_VALUE;
        mIconDrawable = icon;
        mIsCircular = isCircular;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    /**
     * Sets the size of the icon.
     *
     * @param size New icon size.
     */
    public Sneaker setIconSize(int size) {
        mIconSize = size;
        return this;
    }

    /**
     * Sets the corner radius for round corner sneaker.
     *
     * @param radius Corner radius.
     */
    public Sneaker setCornerRadius(int radius) {
        mCornerRadius = radius;
        return this;
    }

    /**
     * Sets the corner radius for round corner sneaker with margin.
     *
     * @param radius Corner radius.
     * @param margin margin.
     */
    public Sneaker setCornerRadius(int radius, int margin) {
        mCornerRadius = radius;
        mMargin = margin;
        return this;
    }

    /**
     * Disable/Enable auto hiding sneaker
     *
     * @param autoHide
     * @return
     */
    public Sneaker autoHide(boolean autoHide) {
        mAutoHide = autoHide;
        return this;
    }

    /**
     * Sets the duration for sneaker.
     * After this duration sneaker will disappear
     *
     * @param duration
     * @return
     */
    public Sneaker setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    /**
     * Sets the click listener to sneaker
     *
     * @param listener
     * @return
     */
    public Sneaker setOnSneakerClickListener(OnSneakerClickListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * Sets the dismiss listener to sneaker
     */
    public Sneaker setOnSneakerDismissListener(OnSneakerDismissListener listener) {
        mDismissListener = listener;
        return this;
    }

    public Sneaker setDismissOnTouch(boolean dismissOnTouch) {
        mDismissOnTouch = dismissOnTouch;
        return this;
    }

    /**
     * Set font for title and message
     *
     * @param typeface
     * @return
     */
    public Sneaker setTypeface(Typeface typeface) {
        mTypeFace = typeface;
        return this;
    }

    /**
     * Shows sneaker with custom color
     *
     * @param backgroundColor Color resource for sneaker background color
     */
    public void sneak(int backgroundColor) {
        if (getContext() != null) {
            try {
                mBackgroundColor = ContextCompat.getColor(getContext(), backgroundColor);
            } catch (Exception e) {
                mBackgroundColor = backgroundColor;
            }
            sneakView();
        }
    }

    private static Handler handler = new Handler();

    /**
     * Creates the view and sneaks in
     */
    private void sneakView() {

        // Main layout
        LinearLayout layout = new LinearLayout(getContext());
        layoutWeakReference = new WeakReference<>(layout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        if (mMargin != DEFAULT_VALUE) {
            layoutParams.setMargins(Utils.convertToDp(getContext(), mMargin),
                    Utils.convertToDp(getContext(), mMargin) + Utils.getStatusBarHeight((Activity) getContext()),
                    Utils.convertToDp(getContext(), mMargin),
                    Utils.convertToDp(getContext(), mMargin)
            );
        }
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setMinimumHeight(Utils.convertToDp(getContext(), 80));
        layout.setPadding(Utils.convertToDp(getContext(), 12), Utils.convertToDp(getContext(), 12),
                Utils.convertToDp(getContext(), 12), Utils.convertToDp(getContext(), 12));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(Utils.convertToDp(getContext(), 6));
        }
        // Background color
        if (mCornerRadius == DEFAULT_VALUE) {
            layout.setBackgroundColor(mBackgroundColor);
        } else {
            Utils.customView(getContext(), layout, mBackgroundColor, mCornerRadius);
        }

        // Icon
        // If icon is set
        if (mIcon != DEFAULT_VALUE || mIconDrawable != null) {
            if (!mIsCircular) {
                AppCompatImageView ivIcon = new AppCompatImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Utils.convertToDp(getContext(), mIconSize), Utils.convertToDp(getContext(), mIconSize));
                ivIcon.setLayoutParams(lp);

                if (mIcon == DEFAULT_VALUE) {
                    ivIcon.setImageDrawable(mIconDrawable);
                } else {
                    ivIcon.setImageResource(mIcon);
                }
                ivIcon.setClickable(false);
                if (mIconColorFilterColor != DEFAULT_VALUE) {
                    ivIcon.setColorFilter(mIconColorFilterColor);
                }
                layout.addView(ivIcon);
            } else {
                RoundedImageView ivIcon = new RoundedImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Utils.convertToDp(getContext(), mIconSize), Utils.convertToDp(getContext(), mIconSize));
                ivIcon.setLayoutParams(lp);

                if (mIcon == DEFAULT_VALUE) {
                    ivIcon.setImageDrawable(mIconDrawable);
                } else {
                    ivIcon.setImageResource(mIcon);
                }
                ivIcon.setClickable(false);
                if (mIconColorFilterColor != DEFAULT_VALUE) {
                    ivIcon.setColorFilter(mIconColorFilterColor);
                }
                layout.addView(ivIcon);
            }
        }

        // Title and description
        LinearLayout textLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayout.setLayoutParams(textLayoutParams);
        textLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lpTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (!mMessage.isEmpty()) {
            TextView tvMessage = new TextView(getContext());
            tvMessage.setLayoutParams(lpTv);
            tvMessage.setGravity(Gravity.CENTER_VERTICAL);
            tvMessage.setPadding(Utils.convertToDp(getContext(),16), Utils.convertToDp(getContext(),8), Utils.convertToDp(getContext(),16), Utils.convertToDp(getContext(),8)); // No bottom padding if there is no title

            if (mMessageColor != DEFAULT_VALUE)
                tvMessage.setTextColor(mMessageColor);

            // typeface
            if (mTypeFace != null)
                tvMessage.setTypeface(mTypeFace);

            tvMessage.setTextSize(mTextSize);
            tvMessage.setText(mMessage);
            tvMessage.setClickable(false);
            textLayout.addView(tvMessage);

        }
        layout.addView(textLayout);
        layout.setId(R.id.mainLayout);

        final ViewGroup viewGroup = getActivityDecorView();
        getExistingOverlayInViewAndRemove(viewGroup);

        layout.setOnClickListener(this);
        viewGroup.addView(layout);


        layout.clearAnimation();
        layout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_show));
        if (mAutoHide) {
            handler.removeCallbacksAndMessages(null);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (getLayout() != null && getContext() != null) {
                        getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
                        viewGroup.removeView(getLayout());
                    }
                }
            };

            handler.postDelayed(runnable, mDuration);
        }
    }

    /**
     * Gets the existing sneaker and removes before adding new one
     *
     * @param parent
     */
    private void getExistingOverlayInViewAndRemove(ViewGroup parent) {

        for (int i = 0; i < parent.getChildCount(); i++) {

            View child = parent.getChildAt(i);
            if (child.getId() == R.id.mainLayout) {
                parent.removeView(child);
            }
            if (child instanceof ViewGroup) {
                getExistingOverlayInViewAndRemove((ViewGroup) child);
            }
        }
    }

    /**
     * Sneaker on click
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        if (!mDismissOnTouch) {
            return;
        }

        if (mListener != null) {
            mListener.onSneakerClick(view);
        }
        if (getLayout() != null && getContext() != null) {
            getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
            getActivityDecorView().removeView(getLayout());
        }
    }
}
