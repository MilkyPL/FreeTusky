package com.thechiefmeat.freetusky.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thechiefmeat.freetusky.R;
import com.thechiefmeat.freetusky.entity.Account;
import com.thechiefmeat.freetusky.interfaces.AccountActionListener;
import com.thechiefmeat.freetusky.util.CustomEmojiHelper;
import com.thechiefmeat.freetusky.util.ImageLoadingHelper;

public class MutesAdapter extends AccountAdapter {

    public MutesAdapter(AccountActionListener accountActionListener) {
        super(accountActionListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
            case VIEW_TYPE_ACCOUNT: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_muted_user, parent, false);
                return new MutesAdapter.MutedUserViewHolder(view);
            }
            case VIEW_TYPE_FOOTER: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_footer, parent, false);
                return new LoadingFooterViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ACCOUNT) {
            MutedUserViewHolder holder = (MutedUserViewHolder) viewHolder;
            holder.setupWithAccount(accountList.get(position));
            holder.setupActionListener(accountActionListener);
        }
    }


    static class MutedUserViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView username;
        private TextView displayName;
        private ImageButton unmute;
        private String id;
        private boolean animateAvatar;

        MutedUserViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.muted_user_avatar);
            username = itemView.findViewById(R.id.muted_user_username);
            displayName = itemView.findViewById(R.id.muted_user_display_name);
            unmute = itemView.findViewById(R.id.muted_user_unmute);
            animateAvatar = PreferenceManager.getDefaultSharedPreferences(itemView.getContext())
                    .getBoolean("animateGifAvatars", false);
        }

        void setupWithAccount(Account account) {
            id = account.getId();
            CharSequence emojifiedName = CustomEmojiHelper.emojifyString(account.getName(), account.getEmojis(), displayName);
            displayName.setText(emojifiedName);
            String format = username.getContext().getString(R.string.status_username_format);
            String formattedUsername = String.format(format, account.getUsername());
            username.setText(formattedUsername);
            int avatarRadius = avatar.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.avatar_radius_48dp);
            ImageLoadingHelper.loadAvatar(account.getAvatar(), avatar, avatarRadius, animateAvatar);
        }

        void setupActionListener(final AccountActionListener listener) {
            unmute.setOnClickListener(v -> listener.onMute(false, id, getAdapterPosition()));
            avatar.setOnClickListener(v -> listener.onViewAccount(id));
        }
    }
}
