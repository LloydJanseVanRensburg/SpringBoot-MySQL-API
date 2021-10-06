package za.co.nwu.accountsystemapi.milesaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/miles")
public class MilesAccountController {

    @Autowired
    MilesAccountService milesAccountService;

    @GetMapping("/")
    public List<MilesAccount> getMilesAccounts() {
        return milesAccountService.getMilesAccounts();
    }

    @GetMapping("/{id}")
    public MilesAccount getMilesAccount(@PathVariable int id) {
        return milesAccountService.getMilesAccount(id);
    }

    @PostMapping("/")
    public MilesAccount createMilesAccount(@RequestBody MilesAccount milesAccount ) {
        return milesAccountService.createMilesAccount(milesAccount);
    }

    @PutMapping("/{id}/add")
    public MilesAccount addMilesToAccount(@PathVariable int id, @RequestBody int milesValue) {
        return milesAccountService.addMilesToAccount(id, milesValue);
    }

    @PutMapping("/{id}/remove")
    public MilesAccount removeMilesFromAccount(@PathVariable int id, @RequestBody int milesValue) {
        return milesAccountService.removeMilesFromAccount(id, milesValue);
    }

    @DeleteMapping("/{id}")
    public void deleteMilesAccount(@PathVariable int id) {
        milesAccountService.deleteMilesAccount(id);
    }
}
