package com.example.recycle1.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recycle1.R;
import com.example.recycle1.data.model.Course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.recycle1.views.HomeActivity.inHome;

public class CourseShowFragment  extends Fragment implements CourseFragment.CourseActivityListener, HomeActivity.CourseActivityListener {

    Course courseShow;
    ArrayList<Course> courseList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.detail);
        inHome = false;

    }
    @Override
    public void onCourseSent(Course course) {
        courseShow = course;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.course_show_fragment, container, false);
        TextView id = v.findViewById(R.id.id_tv);
        TextView createdAt = v.findViewById(R.id.createdat_tv);
        TextView name = v.findViewById(R.id.theme_tv);
        TextView horraire = v.findViewById(R.id.heure_tv);
        TextView lieu = v.findViewById(R.id.place_tv);
        TextView endon = v.findViewById(R.id.endon_tv);
        id.setText(courseShow.getId());
        name.setText(courseShow.getName());
        horraire.setText(getDate(courseShow.getStartOn()));
        lieu.setText(courseShow.getAddress() + " " + courseShow.getZip() + " "+ courseShow.getCity());
        endon.setText(getDate(courseShow.getEndOn()));
        createdAt.setText(getDate(courseShow.getCreatedAt()));
        return v;
    }

    public void setCourseShow(Course courseShow) {
        this.courseShow = courseShow;
    }

    public Course getCourseShow() {
        return courseShow;
    }
    private String getDate(String timestamp){
        long timeStamp = Long.parseLong(timestamp);

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return ex.toString();
        }
    }
}