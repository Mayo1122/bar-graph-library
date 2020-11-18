package com.macrohard.bargraphlibrary

import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Base fragment class for All fragments
 */
    abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private var isRestoredFromBackStack: Boolean = false

    /**
     * Getter method for View data binding
     *
     * @return ViewDataBinding of type T
     */
    lateinit var binding: T

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRestoredFromBackStack = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isRestoredFromBackStack = true
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executePendingBindings()
    }

    /**
     * Determines if Fragment is created for first time or it is being restored from back stack
     *
     * @return True if Restored from back stack and False if created first time
     */
    fun isRestoredFromBackStack(): Boolean {
        return isRestoredFromBackStack
    }

    /**
     * This method will add Fragment to child's fragment manager
     *
     * @param fragment Fragment to add (First fragment with in fragment)
     * @param contentId Container ID
     */
    fun addFragment(fragment: Fragment, contentId: Int) {
        fragment.retainInstance = true
        childFragmentManager.beginTransaction()
                .add(contentId, fragment, fragment.javaClass.name).commit()
    }

    /**
     * This method will replace Fragment and add that Fragment to child's fragment manager's back stack
     *
     * @param fragment Current Fragment
     * @param contentId Container ID
     */
    fun replaceFragment(fragment: Fragment, contentId: Int) {
        fragment.retainInstance = true
        childFragmentManager.beginTransaction()
                .replace(contentId, fragment, fragment.javaClass.name)
                .addToBackStack(fragment.javaClass.name).commitAllowingStateLoss()
    }

    /**
     * This method will return current fragment hosted in container with provided ID
     * @param contentId Container ID
     * @return Current Fragment
     */
    fun getCurrentFragment(contentId: Int): Fragment? {
        return childFragmentManager.findFragmentById(contentId)
    }
}
