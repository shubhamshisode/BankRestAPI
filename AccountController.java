package com.shubhamshisode.bankrest12.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shubhamshisode.bankrest12.entities.Account;
import com.shubhamshisode.bankrest12.services.AccountService;

@RestController
@RequestMapping("/bank/api")
@CrossOrigin(origins = "*")   // apply once for all APIs
public class AccountController {

    @Autowired
    private AccountService accServices;

    // GET -> http://localhost:8083/bank/api/all
    @GetMapping("/all")
    public List<Account> getAll() {
        return accServices.getAllAccounts();
    }

    // GET -> http://localhost:8083/bank/api/search/1035
    @GetMapping("/search/{accno}")
    public Account searchAccount(@PathVariable int accno) {
        return accServices.getOneAccount(accno);
    }

    // GET -> http://localhost:8083/bank/api/search/type/saving
    @GetMapping("/search/type/{acctype}")
    public List<Account> getByType(@PathVariable String acctype) {
        return accServices.searchByType(acctype);
    }

    // POST -> http://localhost:8083/bank/api/add
    @PostMapping("/add")
    public Account addAccount(@RequestBody Account acc) {
        return accServices.addNewAccount(acc);
    }

    // PUT -> http://localhost:8083/bank/api/deposit?accno=101&amount=500
    @PutMapping("/deposit")
    public String deposit(
            @RequestParam int accno,
            @RequestParam float amount) {
        return accServices.depositAccount(accno, amount);
    }

    // DELETE -> http://localhost:8083/bank/api/close?accno=101
    @DeleteMapping("/close")
    public String closeAccount(@RequestParam int accno) {
        return accServices.deleteAccount(accno);
    }

    // PUT -> http://localhost:8083/bank/api/transfer?fromacc=101&toacc=102&amount=500
    @PutMapping("/transfer")
    public String transferAmount(
            @RequestParam int fromacc,
            @RequestParam int toacc,
            @RequestParam float amount) {

        return accServices.transfer(fromacc, toacc, amount);
    }
}
