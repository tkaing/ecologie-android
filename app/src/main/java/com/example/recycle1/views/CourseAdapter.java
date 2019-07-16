package com.example.recycle1.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import com.example.recycle1.R;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private ItemClickListener itemClickListener;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_parcours, viewGroup, false);
        return new CourseViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        Course course = courseList.get(i);
        courseViewHolder.nameTv.setText(course.getTheme());
        courseViewHolder.idTv.setText(course.getId());
        if (itemClickListener != null) {
            courseViewHolder.itemView.setOnClickListener(v -> itemClickListener.onClick(course));
        }
    }

    @Override public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.file_name_tv) TextView nameTv;
        @BindView(R.id.item_course_id_tv) TextView idTv;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Course course);
    }
}


