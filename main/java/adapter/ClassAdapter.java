package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasedisplay.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassCardHolder> {
    public int count;
    public List<String> names;
    public ClassAdapter(int count) {
        this.count = count;
    }
    public void setName (List<String> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public ClassCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ClassCardHolder(inflater.inflate(R.layout.custom_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ClassCardHolder holder, int position) {
        holder.textView.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return count;
    }

    static class ClassCardHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ClassCardHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

}

