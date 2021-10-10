package za.co.nwu.accountsystemapi.milesaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MilesAccountService {

    @Autowired
    MilesAccountRepository milesAccountRepository;

    public MilesAccountService(MilesAccountRepository milesAccountRepository) {
        this.milesAccountRepository = milesAccountRepository;
    }

    @Transactional
    public List<MilesAccount> getMilesAccounts()
    {
        return milesAccountRepository.findAll();
    }

    @Transactional
    public MilesAccount getMilesAccount(int id) {
        return milesAccountRepository.findById(id).orElseThrow(() -> new ApiRequestException("Account Not Found"));
    }

    @Transactional
    public MilesAccount createMilesAccount(MilesAccount milesAccount) {
        Optional<MilesAccount> foundMilesAccountByUserId =
                milesAccountRepository.findMilesAccountByUserId(milesAccount.getUserId());

        if(!foundMilesAccountByUserId.isEmpty()) {
            throw new ApiRequestException("This user already has an MilesAccount");
        }

        return milesAccountRepository.save(milesAccount);
    }

    @Transactional
    public MilesAccount addMilesToAccount(int id, int milesValue) {
        MilesAccount currentMilesAccount = milesAccountRepository.findById(id).orElseThrow(() -> new ApiRequestException("Miles Account Not Found"));

        int sum = currentMilesAccount.getTotalMilesCount() + milesValue;

        currentMilesAccount.setTotalMilesCount(sum);

        return milesAccountRepository.save(currentMilesAccount);
    }

    @Transactional
    public MilesAccount removeMilesFromAccount(int id, int milesValue) {
        MilesAccount currentMilesAccount = milesAccountRepository.findById(id).orElseThrow(() -> new ApiRequestException("Miles Account Not Found"));

        int sum = currentMilesAccount.getTotalMilesCount() - milesValue;

        if(sum < 0) {
            throw new IllegalStateException("Max miles balance reached");
        }

        currentMilesAccount.setTotalMilesCount(sum);

        return milesAccountRepository.save(currentMilesAccount);
    }

    @Transactional
    public void deleteMilesAccount(int id) {
        Optional<MilesAccount> foundMilesAccount = milesAccountRepository.findById(id);

        if(foundMilesAccount.isEmpty()) {
            throw new ApiRequestException("Miles Account Not Found");
        }

        milesAccountRepository.deleteById(id);
    }
}
