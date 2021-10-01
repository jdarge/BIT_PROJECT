// JAN DARGE

// THIS SHORT PROJECT WAS THE INSPIRATION OF THIS CURRENT PROJECT

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

#define INPUT_8 0xCC
#define SIZE 8

char *HexToBin_8(uint8_t a) {
    int x = 0;
    char *res = (char *) malloc(sizeof(char) * SIZE + 1);

    for (uint8_t i = 0x80; i != 0; i >>= 1) {
        res[x++] = (a & i) ? '1' : '0';
    }

    return res;
}

char *HexToFlippedBin_8(uint8_t a) {
    int x = 0;
    char *res = (char *) malloc(sizeof(char) * SIZE + 1);

    for (uint8_t i = 0x80; i != 0; i >>= 1) {
        res[x++] = (a & i) ? '0' : '1';
    }

    return res;
}

int main(void) {
    char *output;

    output = HexToBin_8(INPUT_8);
    printf("UNFLIPPED BINARY:\t%s\n", output);
    free(output);

    output = HexToFlippedBin_8(INPUT_8);
    printf("FLIPPED BINARY: \t%s\n", output);\
    free(output);
}