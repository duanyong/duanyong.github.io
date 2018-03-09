#include <stdio.h>

#define BITSPERWORD 32      //由于一个int采用4字节32位bit，
#define SHIFT       5       //由于2^5为32，所以设置数字的位移量
#define MASK        0x1F    //用于取余操作，如  & MASK = 
#define N           1000000


void main(int argc, char * argv[]) {
    int bitmap[(N -1) / BITSPEWORD];

    void SET(int i) {        bitmap[i >> SHIFT] |=  ( 1 << ( i & MASK) ); }
    void CLR(int i) {        bitmap[i >> SHIFT] &= ~( 1 << ( i & MASK) ); }
    void test(int i){ return bitmap[i >> SHIFT] &   ( 1 << ( i & MASK )); }

    FILE *fp = fopen("./test", "w");

    if (fp == null) {
        printf("Cann't create test file.");

        return ;
    }


    if (null == createBitSet(fp)) {
        //写入失败
        close(fp);

        return ;
    }


}


