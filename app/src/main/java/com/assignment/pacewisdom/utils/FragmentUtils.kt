package com.assignment.pacewisdom.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.assignment.pacewisdom.R


object FragmentUtils {
    var sDisableFragmentAnimations = false

    /**
     * Push fragment into container
     *
     * @param activity       required to get fragment manager
     * @param containerId    id of container where fragment will be added
     * @param fragment       fragment to add
     * @param bundle         data for fragment
     * @param tag            name of fragment to add
     * @param addToBackStack true if you want to add in back stack
     * @param animate        fragment
     */
    /*fun pushFragment(
        activity: FragmentActivity, containerId: Int, fragment: Fragment?,
        bundle: Bundle?, tag: String?, addToBackStack: Boolean,
        animate: Boolean
    ) {
        try {
            if (fragment == null) {
                return
            }
            if (bundle != null) {
                fragment.arguments = bundle
            }
            val ft: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            if (animate) {
                ft.setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
            }
            if (addToBackStack) {
                ft.addToBackStack(null)
            }
            if (!fragment.isAdded) {
                ft.replace(containerId, fragment, tag).commit()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    /**
     * Remove added fragment from specific container id
     *
     * @param activity    to get Fragment Manager
     * @param containerId of frame layout
     */
    fun removeFragmentFromContainer(activity: AppCompatActivity, containerId: Int) {
        val ft: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        if (ft != null) {
            activity.supportFragmentManager.findFragmentById(containerId)?.let { ft.remove(it) }
            ft.commit()
        }
    }

    /**
     * Removes all fragments from backstack
     *
     * @param activity required to get fragment manager
     */
    fun removeAllFragmentsFromBackStack(activity: AppCompatActivity) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        sDisableFragmentAnimations = true
        if (fragmentManager != null) {
            val count: Int = fragmentManager.backStackEntryCount
            for (i in count downTo 0) {
                fragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )

                // fragmentManager.popBackStack();
            }
        }
    }

    /**
     * Checks if specific fragment is in back stack
     *
     * @param activity required to get fragment manager
     * @param tag      of fragment to find
     * @return
     */
    fun isFragmentInStack(activity: AppCompatActivity, tag: String?): Boolean {
        var inStack = false
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragment: Fragment = fragmentManager.findFragmentByTag(tag)!!
        if (fragment != null) {
            inStack = true
        }
        return inStack
    }

    fun getFragmentByTag(activity: AppCompatActivity, tag: String?): Fragment? {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        if (fragmentManager != null) {
            val fragment: Fragment = fragmentManager.findFragmentByTag(tag)!!
            if (fragment != null) return fragment
        }
        return null
    }

    fun removeFragmentByTag(activity: AppCompatActivity, tag: String?) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        if (fragmentManager != null) {
            val fragment: Fragment = fragmentManager.findFragmentByTag(tag)!!
            if (fragment != null) fragmentManager.beginTransaction().remove(fragment).commit()
        }
    }
}