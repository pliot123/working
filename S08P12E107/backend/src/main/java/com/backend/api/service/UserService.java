package com.backend.api.service;

import com.backend.api.request.TelEmailReq;
import com.backend.api.request.SignUpReq;
import com.backend.db.entity.User;
import com.backend.db.repository.UserRepository;
import com.backend.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileHandler fileHandler;
    @Autowired
    public UserService(UserRepository userRepository,FileHandler fileHandler) {
        this.userRepository = userRepository;
        this.fileHandler = fileHandler;
    }


    @Transactional
    public User signup(final SignUpReq signUpReq) throws UnknownHostException {

//        if (userRepository.findOneWithRolesByEmail(signUpReq.getEmail()).orElse(null) != null) {
//            throw new EmailDuplicateException(signUpReq);
//        }
//
//        Role role = Role.builder()
//                .roleId(1)
//                .roleName("ROLE_USER")
//                .build();
//
//        String authKey = mailService.sendAuthMail(signUpReq.getEmail(), signUpReq.getNickname());
//        int imgNum = (int) (Math.random()*25 + 1);
//
        User user = User.builder()
                .email(signUpReq.getEmail())
                .password(signUpReq.getPassword())
                .nickname(signUpReq.getNickname())
                .gender(signUpReq.getGender())
                .telNumber(signUpReq.getTelNumber())
                .build();
        //사진이 들어올 경우
        //작업 넣어주고 userSeuqence빼주고 사진을 넣어주면
        //imgpath sequnce바탕으로 경로 짜줘야함
        return userRepository.save(user);
    }




    public User findUserByEmail(String email) {
        User user = userRepository.findOneByEmail(email);
        return user;
    }

    public Boolean checkDuplicateEmail(String email){
        System.out.println(userRepository.findAllByEmail(email).size());
        if(userRepository.findAllByEmail(email).size() > 0) {
            System.out.println("dkdkdk");
            return false;
        }
        return true;
    }

    public boolean checkDuplicateNickname(String nickname) {
        if(userRepository.
                findAllByNickname(nickname).size() > 0) {
            return false;
        }
        return true;
    }


    @Transactional
    public void deleteUser(int userSequence) {
        System.out.println(userSequence);
        userRepository.deleteByUserSequence(userSequence);
    }

    public String getNick(Integer userSequence) {
        User user = userRepository.findByUserSequence(userSequence);
        return user.getNickname();
    }

    public User getOne(Integer userSequence) {
        return userRepository.findByUserSequence(userSequence);
    }

    public User getFindByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public User FindByNick(String nickname) {
        return  userRepository.findByNickname(nickname);
    }

    public void changeNick(Integer userSequence, String nickName) {
        User cur = userRepository.findByUserSequence(userSequence);
        System.out.println(userSequence);
        System.out.println(nickName);
        cur.setNickname(nickName);
        userRepository.save(cur);
    }

    public List<User> getList(Integer userSequence) {
        List<User> list = userRepository.findUser(userSequence);
        return list;
    }

    public boolean crossCheck(TelEmailReq telEmailReq) {
        User user1 = userRepository.findByTelNumber(telEmailReq.getTelNum());
        User user2 = userRepository.findOneByEmail(telEmailReq.getEmail());

        if(user1==user2){
            return true;
        }
        return false;
    }

    public List<User> searchByNick(String word) {
        List<User> list = userRepository.findByNicknameLike(word);
        return list;
    }
}
