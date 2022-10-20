#include<stdio.h>

int main()

{
int no;
printf("enter the no between 1-7");
scanf("%d",&no);
switch(no)
{
 case 1:
 printf("monday");   
 break;
 case 2:
 printf("\ntuesday");
 break;
 case 3:
 printf("\nwednesday");
 break;
  case 4:
 printf("\nthursday");   
 break;
 case 5:
 printf("\nfriday");
 break;
 case 6:
 printf("\nsaturday");
 break;
 case 7:
 printf("\nsunday");
 break;
}
return 0;

}