package com.emreilgar.service;

import com.emreilgar.exception.ErrorType;
import com.emreilgar.exception.UserProfileMicroserviceException;
import com.emreilgar.repository.IUserProfileRepository;
import com.emreilgar.repository.UserProfile;
import com.emreilgar.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    public List<UserProfile> findAll(Long userid){
        /**findById ile verdiğimiz userid yi repo da aratıyoruz, varsa Optional UserProfile Listesini açıyoruz*/
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(userid);
        if (userProfile.isEmpty())
            throw new UserProfileMicroserviceException(ErrorType.UNAUTHORIZED_REQUEST);
        return findAll(); //burada findall serviceManager da ki findAll metodu
    }

    public UserProfileService(IUserProfileRepository repository) {
        super(repository); //serviceManager'a gönderdik
        this.repository = repository; //serviceManager'a geçmiş olan repository'i buraya setliyoruz.
    }
}
