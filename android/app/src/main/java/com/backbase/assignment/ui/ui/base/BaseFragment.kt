package com.backbase.assignment.ui.ui.base

import android.content.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.*

abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel<*>> : Fragment() {
    var baseActivity: BaseActivity<*, *>? = null
    var mRootView: View? = null
    private var mRootViewGrp: ViewGroup? = null
    var viewDataBinding: T? = null
    private var mViewModel: V? = null
    private var mToast: Toast? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (viewDataBinding?.root == null) {
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mRootView = viewDataBinding!!.root
            mRootViewGrp = container
            mRootView
        } else {
            viewDataBinding?.root
        }
    }


    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    /**
     * common toast show for all screens
     *
     */
    fun putToast(message: String?) {
        if (mToast != null) mToast?.cancel()
        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        mToast?.show()
    }

}