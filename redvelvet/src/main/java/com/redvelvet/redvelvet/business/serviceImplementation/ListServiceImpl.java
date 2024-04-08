package com.redvelvet.redvelvet.business.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.redvelvet.redvelvet.business.entities.RecipeList;
import com.redvelvet.redvelvet.business.entities.User;
import com.redvelvet.redvelvet.business.exceptions.ListNotFoundException;
import com.redvelvet.redvelvet.business.services.ListService;
import com.redvelvet.redvelvet.business.repositories.ListRepository;
import com.redvelvet.redvelvet.business.repositories.UserRepository;

public class ListServiceImpl implements ListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public void deleteList(Long id) {
        
        Optional<RecipeList> opList = listRepository.findById(id);

        if(opList.isPresent()){

            RecipeList recipeList = opList.get();

            User user = recipeList.getUser();

            listRepository.deleteById(id);

            user.removeList(id);

            userRepository.save(user);

        } else {

            throw new ListNotFoundException("list was not found");

        }

    }

    @Override
    public void createList(String name, Long userId) {
        Optional<User> opUser = userRepository.findById(userId);

        if (opUser.isPresent()) {
            RecipeList recipeList = new RecipeList(name, opUser.get());

            User user = opUser.get();

            user.addList(recipeList);
            
            listRepository.save(recipeList);

            userRepository.save(user);

        } else {

            throw new UsernameNotFoundException("User was not found");

        }


    }
    
}
