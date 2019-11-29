import java.lang.Math;

public class Cache {

    public static int address[] = {0x0000, 0x0004, 0x000c, 0x2200, 0x00d0, 0x00e0, 0x1130, 0x0028,
            0x113c, 0x2204, 0x0010, 0x0020, 0x0004, 0x0040, 0x2208, 0x0008,
            0x00a0, 0x0004, 0x1104, 0x0028, 0x000c, 0x0084, 0x000c, 0x3390,
            0x00b0, 0x1100, 0x0028, 0x0064, 0x0070, 0x00d0, 0x0008, 0x3394};
    public static boolean hits[] = new boolean[32];
    public static int cache[][] = new int[8][8];
    public static int tempCache[] = new int[32];
    public static int BytesPerLine;
    public static int kWay;
    public static int N;
    public static int bytesTotal;
    public static int setmask;
    public static int tagshift;
    public static int taglength;
    public static int H;
    public static int M;

    public Cache(int l, int k) {
        BytesPerLine = l;
        kWay = k;
        bytesTotal = 128;
        N = bytesTotal / (BytesPerLine * kWay);
        int setbits = (int) (Math.log(N) / Math.log(2));
        setmask = N-1;
        tagshift = 4+setbits;
        taglength = 16-tagshift;

        for(int i = 0; i < hits.length; i++){
            hits[i] = false;
        }

        for(int i = 0; i < 8; i++){
            tempCache[i] = -1;
        }

        for(int i = 0; i < cache.length; i++) {
            for(int j =0; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }

        H = 0;
        M = 0;

    }

    public static void makeNewCache(int set, int i) {
        tempCache[0] = cache[set][i];

        for(int j = 0; j < i; j++) tempCache[j+1] = cache[set][j];

        for(int j = 0; j < i; j++) cache[set][j] = tempCache[j];

        for(int j = 0; j < tempCache.length; j++) {
            tempCache[j] = -1;
        }
    }

    public static void calculateHitMiss() {

        int a, tmp, set, tag;
        for(int i = 0; i < 32; i++) {
            a = address[i];
            tmp = (a >> 4);
            set = tmp & setmask;
            tag = (a>>tagshift);

            for(int j = 0; j < kWay; j++) {
                if (tag == cache[set][j]) {
                    hits[i]=true;
                    makeNewCache(set,j);
                    H++;
                    continue;
                }
            }

            if(!hits[i]) {
                cache[set][N-1] = tag;
                makeNewCache(set, (N-1));
                M++;
            }
        }
    }

    public static void main (String [] args) {
        int l = 16;
        int k = 8;
        Cache cache = new Cache(l, k);
        cache.calculateHitMiss();
        System.out.println("Hits = " + H + "\nMisses = " + M);
    }
}
