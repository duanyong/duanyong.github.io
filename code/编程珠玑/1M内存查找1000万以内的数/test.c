#include <stdio.h>

void main(int argc, char * argv[]) {

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


