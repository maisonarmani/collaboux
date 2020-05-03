package com.randomsturvs.collaboux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class GoalService{

    @Autowired
    GoalRepository goalRepo;

    public void saveGoal(String type){
        Goal goal = new Goal();
        goal.setExercise(type);
        goalRepo.save(goal);
    }

}
