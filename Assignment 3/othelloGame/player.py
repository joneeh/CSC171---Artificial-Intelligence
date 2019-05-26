BLACK = 1
WHITE = 2

from evaluator import Evaluator
from minimax import Minimax
import random, time


def change_color(color):
    if color == BLACK:
        return WHITE
    else:
        return BLACK


class Human:

    def __init__(self, gui, color="black"):
        self.color = color
        self.gui = gui

    def get_move(self):

        validMoves = self.current_board.get_valid_moves(self.color)
        while True:
            move = self.gui.get_mouse_input()
            if move in validMoves:
                break
        self.current_board.apply_move(move, self.color)
        return 0, self.current_board

    def get_current_board(self, board):
        self.current_board = board


class Computer(object):

    def __init__(self, color, prune=3):
        self.depthLimit = prune
        evaluator = Evaluator()
        self.minimaxObj = Minimax(evaluator.score)
        self.color = color

    def get_current_board(self, board):
        self.current_board = board

    def get_move(self):
        return self.minimaxObj.minimax(self.current_board, None, self.depthLimit, self.color,
                                       change_color(self.color))


class RandomPlayer (object):

    def __init__(self, color, prune=3):
        self.depthLimit = prune
        evaluator = Evaluator()
        self.color = color

    def get_current_board(self, board):
        self.current_board = board

    def get_move(self):
        x = random.sample(self.current_board.get_valid_moves(self.color), 1)
        self.current_board.apply_move(x[0], self.color)
        time.sleep(1)
        return 0, self.current_board
