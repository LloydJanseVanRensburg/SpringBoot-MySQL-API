package za.co.nwu.accountsystemapi.milesaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilesAccountService {

    @Autowired
    MilesAccountRepository milesAccountRepository;

    public MilesAccountService(MilesAccountRepository milesAccountRepository) {
        this.milesAccountRepository = milesAccountRepository;
    }

    public List<MilesAccount> getMilesAccounts()
    {
        return milesAccountRepository.findAll();
    }

    public MilesAccount getMilesAccount(int id) {
        return milesAccountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account Not Found"));
    }

    public MilesAccount createMilesAccount(MilesAccount milesAccount) {
        return milesAccountRepository.save(milesAccount);
    }

    public MilesAccount addMilesToAccount(int id, int milesValue) {
        MilesAccount currentMilesAccount = milesAccountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Miles Account Not Found"));

        int sum = currentMilesAccount.getTotalMilesCount() + milesValue;

        currentMilesAccount.setTotalMilesCount(sum);

        return milesAccountRepository.save(currentMilesAccount);
    }

    public MilesAccount removeMilesFromAccount(int id, int milesValue) {
        MilesAccount currentMilesAccount = milesAccountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Miles Account Not Found"));

        int sum = currentMilesAccount.getTotalMilesCount() - milesValue;

        if(sum > 0) {
            throw new IllegalStateException("Max miles balance reached");
        }

        currentMilesAccount.setTotalMilesCount(sum);

        return milesAccountRepository.save(currentMilesAccount);
    }

    public void deleteMilesAccount(int id) {
        milesAccountRepository.deleteById(id);
    }
}
