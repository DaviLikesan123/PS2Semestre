package br.com.fiap.mobilefiap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.mobilefiap.databinding.FragmentPeopleBinding

class PeopleFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState:
                              Bundle?): View? {
        binding = FragmentPeopleBinding.inflate(layoutInflater)
        return binding.root
    }
}