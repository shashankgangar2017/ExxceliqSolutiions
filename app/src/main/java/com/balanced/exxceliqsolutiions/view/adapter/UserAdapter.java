package com.balanced.exxceliqsolutiions.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.balanced.exxceliqsolutiions.R;
import com.balanced.exxceliqsolutiions.wrapper.UserResponse;
import com.bumptech.glide.Glide;

/**
 * This is Adapter class where we display the data on successful response by implementing the PagedListAdapter for pagination purpose.
 */

public class UserAdapter extends PagedListAdapter<UserResponse.User, UserAdapter.ViewHolder> {
    /*private List<UserResponse.User> users;
    private Context context;

    public UserAdapter(List<UserResponse.User> posts, Context context) {
        this.users = posts;
        this.context = context;
    }

    protected UserAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserResponse.User currentUser = users.get(position);
        holder.nameTextView.setText(currentUser.getFirst_name());
        holder.emailTextView.setText(currentUser.getEmail());
        Glide.with(context)

                .load(currentUser.getAvatar())

                .into(holder.avatarImageView);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<UserResponse.User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public static final DiffUtil.ItemCallback<UserResponse.User> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserResponse.User>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserResponse.User oldItem, @NonNull UserResponse.User newItem) {
            return oldItem.getId() == newItem.getId();
        }


        @Override
        public boolean areContentsTheSame(@NonNull UserResponse.User oldItem, @NonNull UserResponse.User newItem) {
            return oldItem.equals(newItem);
        }
    };

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, emailTextView;
        private ImageView avatarImageView;

        private UserViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
        }
    }*/

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse.User user = getItem(position);
        if (user != null) {
            holder.bind(user);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView emailTextView;
        private ImageView avatarImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
        }

        public void bind(UserResponse.User user) {
            nameTextView.setText(user.getFirst_name() + " " + user.getLast_name());
            emailTextView.setText(user.getEmail());

            // Load image using your preferred image loading library (e.g., Picasso, Glide)
            // Here, I assume you're using Glide
            Glide.with(itemView.getContext())
                    .load(user.getAvatar())
                    .into(avatarImageView);
        }
    }

    private static final DiffUtil.ItemCallback<UserResponse.User> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserResponse.User>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserResponse.User oldItem, @NonNull UserResponse.User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserResponse.User oldItem, @NonNull UserResponse.User newItem) {
            return oldItem.equals(newItem);
        }
    };
}
