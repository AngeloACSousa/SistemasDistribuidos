package guiao3;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    public Map<Integer,Conta> contas;
    public int newId = 0;
    ReentrantLock bancoLock = new ReentrantLock();

    public Banco() {
        contas = new HashMap<>();
    }

    public int criarConta(float initialBalance) {
        Conta conta = new Conta(initialBalance);
        bancoLock.lock();
        int id = newId;
        contas.put(id,conta);
        newId++;
        bancoLock.unlock();
        return id;
    }


    public double closeAccount(int id) throws Exception {
        if (contas.containsKey(id)) {
            bancoLock.lock();
            Conta conta = contas.get(id);
            conta.contaLock.lock();
            bancoLock.unlock();
            double valor = conta.consultar();
            conta.contaLock.unlock();
            return valor;
        }
        else throw new Exception("Conta Inválida");
    }

    public double totalBanco(int[] ids){
        double total = 0;
        Arrays.sort(ids);
        List<Conta> contasLock = new ArrayList<>();
        for(int i = 0 ; i < ids.length; i++) {
            bancoLock.lock();
            if (contas.containsKey(ids[i])) {
                Conta c = contas.get(ids[i]);
                c.contaLock.lock();
                contasLock.add(c);
            }
            bancoLock.unlock();
        }
        for(Conta c : contasLock) {
            total += c.consultar();
            c.contaLock.unlock();
        }
        return total;
        }
    }
