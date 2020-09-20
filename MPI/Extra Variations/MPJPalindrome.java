/*
	Name:		Yash D. Gandhi
	Roll No.:	45017	
	Assignment:	2
	
	To develop any distributed application using Message Passing Interface(MPI). 
*/
import mpi.MPI;
import java.util.*;
import java.lang.*;

public class MPJPalindrome {
	public static void main(String args[]) {
		// Initialize MPI execution environment
		MPI.Init(args);
		// Get the id of the process
		int rank = MPI.COMM_WORLD.Rank();
		// total number of processes is stored in size
		int size = MPI.COMM_WORLD.Size();
		int root = 0;
		// array which will be filled with data by root process
		String sendbuf[];

		sendbuf = new String[size];

		// creates data to be scattered
		if (rank == root) {
			sendbuf[0] = "roy";
			sendbuf[1] = "bmw";
			sendbuf[2] = "dad";
			sendbuf[3] = "maam";

			// print current process number
			System.out.print("Processor " + rank + " has data: ");
			for (int i = 0; i < size; i++) {
				System.out.print(sendbuf[i] + " ");
			}
			System.out.println();
		}
		// collect data in recvbuf
		String recvbuf[] = new String[1];

		// following are the args of Scatter method
		// send, offset, chunk_count, chunk_data_type, recv, offset, chunk_count,
		// chunk_data_type, root_process_id
		MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.OBJECT, recvbuf, 0, 1, MPI.OBJECT, root);
		System.out.println("Processor " + rank + " has data: " + recvbuf[0]);
		System.out.println("Processor " + rank + " is checking string for palindrome");
		
		StringBuilder d2 = new StringBuilder();
        d2.append(recvbuf[0]);
        d2 = d2.reverse();
		if (recvbuf[0].equals(String.valueOf(d2)))
			recvbuf[0] = recvbuf[0].concat("_is_palindrome");
		else
			recvbuf[0] = recvbuf[0].concat("_is_not_palindrome");
		// following are the args of Gather method
		// Object sendbuf, int sendoffset, int sendcount, Datatype sendtype,
		// Object recvbuf, int recvoffset, int recvcount, Datatype recvtype,
		// int root)
		MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.OBJECT, sendbuf, 0, 1, MPI.OBJECT, root);
		// display the gathered result
		if (rank == root) {
			System.out.println("Process 0 has data: ");
			for (int i = 0; i < 4; i++) {
				System.out.print(sendbuf[i] + "\n");
			}
		}
		// Terminate MPI execution environment
		MPI.Finalize();
	}
}
/*

					++OUTPUT++

	D:\Codes\DCS CODES\DCS Final codes\2> export MPJ_HOME=D:\Software\mpj-v0_44
	D:\Codes\DCS CODES\DCS Final codes\2> javac -cp $MPJ_HOME/lib/mpj.jar MPJConcat.java
	D:\Codes\DCS CODES\DCS Final codes\2> $MPJ_HOME/bin/mpjrun.sh -np 4 MPJPalindrome


	MPJ Express (0.44) is started in the multicore configuration
	Processor 0 has data: roy bmw dad maam
	Processor 0 has data: roy
	Processor 0 is checking string for palindrome
	Processor 1 has data: bmw
	Processor 1 is checking string for palindrome
	Processor 3 has data: dad
	Processor 3 is checking string for palindrome
	Processor 2 has data: maam
	Processor 2 is checking string for palindrome
	Process 0 has data: 
	roy_is_not_palindrome
	bmw_is_not_palindrome
	dad_is_palindrome
	maam_is_palindrome

*/
