import java.util.Arrays;

/**
 * Created by lpug on 18/09/2017.
 */
public class ProjectEuler {

    /*
    For every n≥1n≥1 the prime-counting function π(n)π(n) is equal to the number of primes not exceeding nn.
    E.g. π(6)=3π(6)=3 and π(100)=25π(100)=25.

    We say that a sequence of integers u=(u0,⋯,um)u=(u0,⋯,um) is a ππ sequence if

    un≥1un≥1 for every nn
    un+1=π(un)un+1=π(un)
    uu has two or more elements
    For u0=10u0=10 there are three distinct ππ sequences: (10,4), (10,4,2) and (10,4,2,1).

    Let c(u)c(u) be the number of elements of uu that are not prime.
    Let p(n,k)p(n,k) be the number of ππ sequences uu for which u0≤nu0≤n and c(u)=kc(u)=k.
    Let P(n)P(n) be the product of all p(n,k)p(n,k) that are larger than 0.
    You are given: P(10)=3×8×9×3=648 and P(100)=31038676032.

    Find P(108)P(108). Give your answer modulo 1000000007.
     */

    public int countPrime(int n) {
        // throw new NotImplementedException();

        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        for (int i=2;i<primes.length;i++) {
            //if the number is prime,
            //then go through all its multiples and make their values false.
            if(primes[i]) {
                for (int j=2;i*j<primes.length;j++) {
                    primes[i*j]=false;
                }
            }
        }
        return  primes.length;
    }

    public boolean isPrime(int n) {
        if(n < 2) {
            // throw new IllegalArgumentException();
            return false;
        }

        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
