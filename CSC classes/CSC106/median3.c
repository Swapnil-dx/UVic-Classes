#include <stdio.h>

int main()
{
    int num1, num2, num3, i, j, a;
	
	printf("Enter a number: \n");
	scanf ("%d",&num1);
	
	printf("Enter a number: \n");
	scanf ("%d",&num2);
	
	printf("Enter a number: \n");
	scanf ("%d",&num3);
	
	int arr[3];
	arr[0]=num1;
	arr[1]=num2;
	arr[2]=num3;
	
	for (i = 0; i < 3; ++i)
    {
        for (j = i + 1; j < 3; ++j)
        {
            if (arr[i] > arr[j])
            {
                a =  arr[i];
                arr[i] = arr[j];
                arr[j] = a;
            }
        }
	}
	
	printf("The median of the 3 numbers is %i", arr[1]);
    return 0;
}