def matrix_chain_order(p):
    """
    Compute the minimum number of scalar multiplications needed to
    multiply a sequence of matrices with given dimensions.

    :param p: A list of matrix dimensions where p[i] is the number of rows of the i-th matrix,
              and p[i+1] is the number of columns of the i-th matrix.
    :return: A tuple (m, s) where m[i][j] contains the minimum number of scalar multiplications
             required to compute the product of matrices i through j, and s[i][j] contains the
             index of the matrix where the split should occur to achieve this minimum.
    """
    n = len(p) - 1  # Number of matrices
    m = [[0] * (n + 1) for _ in range(n + 1)]  # Minimum scalar multiplications
    s = [[0] * n for _ in range(n)]  # Splitting points

    # Chain length l varies from 2 to n
    for l in range(2, n + 1):
        for i in range(n - l + 1):
            j = i + l - 1
            m[i][j] = float('inf')  # Initialize to positive infinity
            for k in range(i, j):
                # Calculate the cost of the multiplication
                cost = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1]
                if cost < m[i][j]:
                    m[i][j] = cost
                    s[i][j] = k

    return m, s

def print_optimal_parentheses(s, i, j):
    """
    Print the optimal parentheses for matrix chain multiplication.

    :param s: The splitting points computed by the matrix_chain_order function.
    :param i: The start index of the matrices to consider.
    :param j: The end index of the matrices to consider.
    """
    if i == j:
        print(f'Matrix {i}', end='')
    else:
        print('(', end='')
        print_optimal_parentheses(s, i, s[i][j])
        print_optimal_parentheses(s, s[i][j] + 1, j)
        print(')', end='')

# Example usage:
matrix_dimensions = [30, 35, 15, 5, 10, 20, 25]
m, s = matrix_chain_order(matrix_dimensions)
print("Minimum number of scalar multiplications:", m[0][-1])
print("Optimal Parentheses:", end=' ')
print_optimal_parentheses(s, 0, len(matrix_dimensions) - 2)
