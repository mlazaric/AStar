#!/bin/python

rows = int(input())
cols = int(input())

matrix = [list(input().strip()) for row in range(rows)]

def draw_nodes():
    row_counter = 0
    counter = 0

    for row in reversed(matrix):
        col_counter = 0

        for char in row:
            if char == '#':
                print(f'\\drawWallNode{{{col_counter}}}{{{row_counter}}}{{{counter}}}')
            else:
                if char == '.':
                    char = ' '

                print(f'\\drawNode{{{col_counter}}}{{{row_counter}}}{{{char}}}{{{counter}}}')

            col_counter += 1
            counter += 1

        row_counter += 1

def draw_edges():
    for row in range(rows):
        for col in range(cols):
            print_edge(row, col, row + 1, col)
            print_edge(row, col, row - 1, col)
            print_edge(row, col, row, col + 1)
            print_edge(row, col, row, col - 1)

def print_edge(row1, col1, row2, col2):
    counter1 = row1 * cols + col1
    counter2 = row2 * cols + col2

    if row2 < 0 or row2 >= rows:
        return
    if col2 < 0 or col2 >= cols:
        return

    if matrix[row1][col1] != '#' and matrix[row2][col2] != '#':
        print(f'\\draw[] ({counter1}) -- ({counter2});')

draw_nodes()
draw_edges()
