import java.util.Scanner;
import java.util.Random;

class Healthbar {
    private byte Health;
    private byte killCounter;
    private final byte maxHealth = 100;
    private final byte minHealth = 0;
    
    public byte getkillCounter() {
    	return killCounter;
    }
    public void setHealth(byte Health) {
        this.Health = Health;
    }

    public byte getHealth() {
        return Health;
    }

    public void increaseHealth(byte Amount) {
    	byte currentHealth = getHealth();
        byte newHealth= (byte) (currentHealth + Amount);
        byte overCapacity = (byte) (maxHealth - currentHealth);
        
        if (newHealth > maxHealth) {
            setHealth(maxHealth);
            System.out.println("Darah bertambah " + overCapacity + "\n");
        } else {
            setHealth(newHealth);
            System.out.println("Darah bertambah " + Amount + "\n");
        }
    }

    public void decreaseHealth(byte Amount) {
    	byte currentHealth = getHealth();
        byte newHealth= (byte) (currentHealth - Amount);
        
        if (newHealth < minHealth) {
            setHealth(minHealth);
            System.out.println("Darah berkurang " + currentHealth+ "\n");
        } else {
            setHealth(newHealth);
            System.out.println("Darah berkurang " + Amount + "\n");
        }
    }

    public boolean alive() {
        return Health > minHealth;
    }

    public void AttackEnemy() {
        Random random = new Random();
        byte outcome = (byte) (random.nextInt(3) + 1);

        switch (outcome) {
            case 1:
                System.out.println("Anda berhasil Mmmbunuh lawan tanpa menerima damage");
                killCounter++;
                break;

            case 2:
                byte Injuredwin = (byte) (random.nextInt(50) + 1);
                decreaseHealth(Injuredwin);
                if (alive()) {
                    System.out.println("Anda berhasil membunuh lawan tetapi menerima " + Injuredwin + "damage");
                    killCounter++;
                } else {
                    System.out.println("Karakter anda mati...... \n\n Terima kasih telah bermain");
                    System.exit(0);
                }
                break;
            case 3:
                byte Injuredloss = (byte) (random.nextInt(31) + 50);
                decreaseHealth(Injuredloss);
                if (alive()) {
                    System.out.println("Anda tidak dapat melawan orang tersebut.\nAnda berhasil kabur tetapi menerima " + Injuredloss + "damage");
                } else {
                    System.out.println("Karakter anda mati...... \n\n Terima kasih telah bermain");
                    System.exit(0);
                }
            default:
                break;
        }
    }

    class Manabar {
        private byte Mana;
        private final byte maxMana = 100;
        private final byte minMana = 0;

        public byte setMana(byte Mana) {
            return this.Mana = Mana;
        }

        public byte getMana() {
            return Mana;
        }

        public void increaseMana(byte amount) {
        	byte currentMana = getMana();
            byte newMana= (byte) (currentMana + amount);
            byte overCapacity = (byte) (maxMana- currentMana);
            
            if (newMana > maxMana) {
                setMana(maxHealth);
                System.out.println("Mana bertambah " + overCapacity + "\n");
            } else {
                setMana(newMana);
                System.out.println("Mana bertambah " + amount + "\n");
            }
        }

        public void decreaseMana(byte amount) {
            byte currentMana = getMana();
            byte newMana = (byte) (currentMana - amount);
            
            if (newMana < minMana) {
                setMana(minMana);
                System.out.println("Mana berkurang " + currentMana + "\n");
            } else {
                setMana(newMana);
                System.out.println("Mana berkurang " + amount + "\n");
            }
        }


        public void useMagic() {
            Random random = new Random();
            byte manaUsed = (byte) (random.nextInt(11) + 20);
            byte healthHealed = (byte) (manaUsed / 2);
            if (Health >= maxHealth) {
                System.out.println("Darah anda masih penuh! Tidak dapat melakukan healing");
            } else if (Mana <= minMana) {
                System.out.println("Mana tidak cukup untuk melakukan healing!");
            } else {
            	if (Mana - manaUsed < minMana) {
            		increaseHealth(manaUsed);
            	}
            	else {
                increaseHealth(healthHealed);
                }
                decreaseMana(manaUsed);
            }
        }

        public void BuynConsume_Potion() {
            Random random = new Random();
            byte PoisonorPotion = (byte) (random.nextInt(5) + 1);
            if (PoisonorPotion == 4) {
                byte Poisoned = (byte) (random.nextInt(26) + 25);
                decreaseHealth(Poisoned);
                if(alive()) {
                	System.out.println("Anda tertipu, potion yang anda minum adalah racun!\n");
                }
                else {
                	System.out.println("Karakter anda mati...... \n\n Terima kasih telah bermain");
                    System.exit(0);
                }
            } else {
                byte manaRestored = (byte) (random.nextInt(26) + 25);
                if(Mana >= maxMana) {
                	System.out.println("Mana sudah penuh, tidak dapat mengisi mana lagi!");
                }	
                else {
                	increaseMana(manaRestored);
                	
                	}
                }
            }
        }
    }


public class Main {
    public static void main(String[] args) {
        Healthbar healthbar = new Healthbar();
        healthbar.setHealth((byte)100);
        Healthbar.Manabar manabar = healthbar.new Manabar();
        manabar.setMana((byte)100);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("HealthBar value: " + healthbar.getHealth());
            System.out.println("ManaBar value: " + manabar.getMana());
            System.out.println("Enemy killed: "+ healthbar.getkillCounter());

            System.out.println("Pilih aksi:");
            System.out.println("1. Attack enemy");
            System.out.println("2. Use magic");
            System.out.println("3. Use Mana potion");
            System.out.println("4. Keluar");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    healthbar.AttackEnemy();
                    break;
                case 2:
                    manabar.useMagic();
                    break;
                case 3:
                    manabar.BuynConsume_Potion();
                    break;
                case 4:
                    System.out.println("Terima kasih sudah bermain.....");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        }
    }
}
