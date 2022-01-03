package com.example.navigation_exam_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // argument 전달
        MainFragmentDirections.ActionMainFragmentToSecondFragment action =
                MainFragmentDirections.actionMainFragmentToSecondFragment("Hello!!!!");
        view.findViewById(R.id.button).setOnClickListener(v ->
                Navigation.findNavController(v).navigate(action)
        );

    }
}