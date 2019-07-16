package com.example.recycle1.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycle1.R;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.model.Course;
import com.example.recycle1.data.service.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

import static com.example.recycle1.views.HomeActivity.inHome;

public class CourseFragment extends Fragment {
    RecyclerView listeCourse;
    private CourseAdapter courseAdapter;
    String coursePath = "" ;
    List<CourseDTO> courseList= new ArrayList<>();
    CourseActivityListener courseListener;
    Course course;

    public interface CourseActivityListener {
        void onCourseSent(Course course);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inHome = false;

        listeCourse = view.findViewById(R.id.recycle_view_courses);


        initRecyclerView();
        loadData();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parcours, container, false);

        return v;
    }
    private void initRecyclerView() {
        listeCourse.setLayoutManager(new LinearLayoutManager(getActivity()));
        courseAdapter = new CourseAdapter();
        listeCourse.setAdapter(courseAdapter);

        courseAdapter.setItemClickListener(course -> {

            courseListener.onCourseSent(course);

            Fragment fragment = new CourseShowFragment();
            ((CourseShowFragment) fragment).setCourseShow(course);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_home, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }

    private void loadData() {
        NetworkProvider.getInstance().getCourses(new NetworkProvider.Listner<List<Course>>() {
            @Override public void onSuccess(List<Course> data) {
                courseAdapter.setCourseList(data);
            }

            @Override public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CourseActivityListener) {
            courseListener = (CourseActivityListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement CourseActivityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        courseListener = null;
    }
}


