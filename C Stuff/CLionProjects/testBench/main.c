#include <stdio.h>
#include <assert.h>

int main()
{
    unsigned char c;  // we need the character to be
    // unsigned to use it as an index
    FILE * file;
    int freqs[256] = {0};


    file = fopen("/Users/zachmeade/CLionProjects/testBench/test", "r");
    assert( file != NULL );
    c = fgetc(file);	// attempt to read a byte
    while( !feof(file) ) {
        printf("%c", c);
        freqs[(int) c] = freqs[(int) c] +1;
        c = fgetc(file);
    }
    fclose(file);
    for (int i = 0; i < 256; i++) {
        unsigned char testChar = i;
        printf("\n %c has a freq of %d", testChar, freqs[i]);
    }
    return 0;  // exit without error code
}