package com.wildcodeschool.bandersnatch.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpSession;

import com.wildcodeschool.bandersnatch.entities.Room;
import com.wildcodeschool.bandersnatch.entities.Score;
import com.wildcodeschool.bandersnatch.repositories.RoomRepository;
import com.wildcodeschool.bandersnatch.repositories.ScoreRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class GameController {
    int currentScore = 0;
   
    private static RoomRepository roomRepository = new RoomRepository();

    @PostMapping("/game")
    public String game(HttpSession session, Model model, @RequestParam(required=false) String nickname, @RequestParam(required=false) String action) {
        if(session.getAttribute("nickname") == null) {
            // first time we get here : check nickname
            if(nickname == null || nickname.equals("")) {
                // invalid nickname : go back to home page
                return "redirect:/";
            } 
            // register nickname in the session
            session.setAttribute("nickname", nickname);
            session.setAttribute("currentRoom", roomRepository.getRoom().get(0));
            session.setAttribute("currentScore", currentScore);    
        }

        if(action != null) {
            Object pseudo = session.getAttribute("nickname");
            // we're playing : check the current action
            Room nextRoom = null;
            int currentRoomId = ((Room)session.getAttribute("currentRoom")).getId();
           
            if(currentRoomId == 1) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(5);
                    // one day: nextRoom = currentRoom.getLeftRoom();
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(2);
                    // one day: nextRoom = currentRoom.getRightRoom();
                }
            }

            if(currentRoomId == 2) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(5);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(3);
                }
            }

            if(currentRoomId == 3) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(4);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(4);
                }
            }

            if(currentRoomId == 4) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(2);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(3);
                }
            }

            if(currentRoomId == 5) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(7);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(6);
                }
            }

            if(currentRoomId == 6) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(8);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(3);
                }
            }
            
            if(currentRoomId == 7) {
                if(action.equals("Left")) {
                    nextRoom = roomRepository.getRoomById(8);
                }
                if(action.equals("Right")) {
                    nextRoom = roomRepository.getRoomById(6);
                }
            }
            if(currentRoomId == 8) {
            // victory
            ScoreRepository.insert((String) pseudo, currentScore);
                return "result";
            }
            currentScore++;
            session.setAttribute("currentRoom", nextRoom);// salle courante = salle suivante
        }


        // we're still playing : show the current choice
        model.addAttribute("nickname", session.getAttribute("nickname"));
        model.addAttribute("currentRoom", session.getAttribute("currentRoom"));
        model.addAttribute("currentScore", session.getAttribute("currentScore"));
        return "game";
    }
}