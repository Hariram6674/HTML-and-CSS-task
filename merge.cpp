#include<iostream>
using namespace std;
#include<vector>
#include <ctime> 
#include<limits.h>
void merge(vector<int> &elements, int left, int mid, int right)
{
	vector<int> left_Sub_Array, right_Sub_Array;
	int no_Left_Sub,no_Right_Sub,i,index_Left_SA,index_Right_SA,index_Full_Array;
	no_Left_Sub = mid-left+1;
	no_Right_Sub = right-mid;
	for(i=0;i<no_Left_Sub;i++)
		left_Sub_Array.push_back(elements[left+i]);
	left_Sub_Array.push_back(INT_MAX);
	for(i=0;i<no_Right_Sub;i++)
		right_Sub_Array.push_back(elements[mid+i+1]);
	right_Sub_Array.push_back(INT_MAX);
	index_Left_SA=0;
	index_Right_SA=0;
	for(index_Full_Array=left;index_Full_Array<=right;index_Full_Array++)
	{
		if(left_Sub_Array[index_Left_SA] < right_Sub_Array[index_Right_SA])
		{
			elements[index_Full_Array] = left_Sub_Array[index_Left_SA];
			index_Left_SA++;
		}
		else
		{
	
			elements[index_Full_Array] = right_Sub_Array[index_Right_SA];
			index_Right_SA++;
		}
	}
}
void mergesort(vector<int> &elements, int left, int right)
{
	int mid;
	if(left==right)
		return;
	mid = (left+right)/2;
	mergesort(elements,left,mid);
	mergesort(elements,mid+1,right);
	merge(elements,left,mid,right);
}
int main()
{
	int n,i,ele;
	vector<int> elements;
	cin>>n;
	for(i=0;i<n;i++)
	{
		cin>>ele;
		elements.push_back(ele);
	}
	clock_t tStart = clock();
	mergesort(elements, 0, n-1);
	double time1=(double)(clock() - tStart)/CLOCKS_PER_SEC;
	cout<<"Time taken is "<<time1<<endl;
	for(i=0;i<n;i++)
	{
		cout<<elements[i]<<"\t";
	}
}


