package com.valoy.disney.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valoy.disney.R
import com.valoy.disney.databinding.CharacterItemBinding
import com.valoy.disney.domain.model.Characters


class CharactersAdapter(private val dataSet: Array<Characters>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class CharacterViewHolder(private val itemBinding: CharacterItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(character: Characters) {
            itemBinding.characterName.text = character.name
            Glide.with(itemBinding.root)
                .load(character.imageUrl) // URL or resource ID
                .placeholder(R.drawable.placeholder) // Optional placeholder
                .error(R.drawable.placeholder_error) // Optional error drawable
                .into(itemBinding.characterImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharacterViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemBinding =
            CharacterItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return CharacterViewHolder(itemBinding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
